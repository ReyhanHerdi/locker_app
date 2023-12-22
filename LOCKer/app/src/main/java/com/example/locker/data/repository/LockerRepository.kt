package com.example.locker.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.example.locker.data.AppExecutors
import com.example.locker.data.ExamplesData
import com.example.locker.data.ResultState
import com.example.locker.data.api.ApiService
import com.example.locker.data.local.database.BookmarkDao
import com.example.locker.data.local.database.BookmarkEntity
import com.example.locker.data.model.Article
import com.example.locker.data.model.History
import com.example.locker.data.model.User
import com.example.locker.data.response.RequestData
import com.example.locker.data.response.TestResponse
import com.example.locker.util.Reference
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await


class LockerRepository private constructor(
    private val apiService: ApiService,
    private val bookmarkDao: BookmarkDao,
    private val executorService: AppExecutors
){

    private val auth = FirebaseAuth.getInstance()
    private val db: FirebaseFirestore by lazy {
        Firebase.firestore
    }

    fun getRecommendation(): List<Article> {
        return ExamplesData.examples

    }

    fun getUser(): String? = auth.uid

    fun login(
        email: String, password: String,
        onSuccess: (AuthResult) -> Unit,
        onFailure: (Exception) -> Unit
    ) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnSuccessListener(onSuccess)
            .addOnFailureListener(onFailure)
    }

    fun register(
        user: User,
        email: String,
        password: String,
        onResult: (Void?, Exception?) -> Unit,
    ) {
        auth.createUserWithEmailAndPassword(email, password).addOnSuccessListener {
            db.collection(Reference.COLLECTION).document(it.user!!.uid)
                .set(user)
                .addOnSuccessListener { documentReference ->
                    onResult(documentReference, null)
                }
                .addOnFailureListener { e ->
                    onResult(null, e)
                }
        }.addOnFailureListener {
            onResult(null, it)
        }
    }

    fun logout() {
        auth.signOut()
    }

    fun getUserData(onResult: (User?, Exception?) -> Unit) {
        db.collection(Reference.COLLECTION).document(auth.currentUser!!.uid)
            .get()
            .addOnSuccessListener { documentSnapshot ->
                val user = documentSnapshot.toObject(User::class.java)
                onResult(user, null)
            }
            .addOnFailureListener { e ->
                onResult(null, e)
            }
    }

    fun saveData(
        user: User,
        onResult: (Void?, Exception?) -> Unit,
    ) {
        db.collection(Reference.COLLECTION).document(auth.currentUser!!.uid)
            .set(user)
            .addOnSuccessListener { documentReference ->
                onResult(documentReference, null)
            }
            .addOnFailureListener { e ->
                onResult(null, e)
            }
    }

    suspend fun addScanHistory(history: History){
        db.collection(Reference.COLLECTION).document(auth.currentUser!!.uid).collection(Reference.HISTORY)
            .add(history)
            .await()
    }

    suspend fun getScanHistory(): List<History> {
        val querySnapshot: QuerySnapshot = db.collection(Reference.COLLECTION).document(auth.currentUser!!.uid)
            .collection(Reference.HISTORY)
            .get()
            .await()

        return querySnapshot.documents.map { documentSnapshot ->
            val jobText = documentSnapshot.getString("jobText") ?: ""
            val result = documentSnapshot.getString("resultScan") ?: ""
            History(jobText, result)
        }
    }

    fun scanJob(text: String): LiveData<ResultState<TestResponse>> =
        liveData {
            emit(ResultState.Loading)
            try {
                val response = apiService.scan(RequestData(text))
                emit(ResultState.Success(response))
            }catch (e: Exception){
                emit(ResultState.Error(e.toString()))
            }
        }


    fun getBookmark(): LiveData<List<BookmarkEntity>> {
        return bookmarkDao.getAllBookmark()
    }

    fun setBookmark(bookmark: BookmarkEntity){
        executorService.networkIO.execute {
            //bookmark.isBookmark = bookmarkState
            bookmarkDao.insert(bookmark)
        }
    }

    fun deleteBookmark(id: Int){
        executorService.mainThread.execute {
            bookmarkDao.delete(id)
        }
    }

    fun isBookmarked(id: Int): Boolean {
        return bookmarkDao.isBookmark(id)
    }

    companion object {
        @Volatile
        private var instances: LockerRepository? = null

        fun getInstance(bookmarkDao: BookmarkDao, appExecutors: AppExecutors, apiService: ApiService): LockerRepository =
            instances ?: synchronized(this){
                instances ?: LockerRepository(apiService, bookmarkDao, appExecutors)
                    .also {
                        instances = it
                    }
            }
    }

}