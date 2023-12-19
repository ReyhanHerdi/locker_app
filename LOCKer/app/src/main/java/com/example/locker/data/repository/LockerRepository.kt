package com.example.locker.data.repository

import com.example.locker.data.ExamplesData
import com.example.locker.data.model.Examples
import com.example.locker.data.model.User
import com.example.locker.util.Reference
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


class LockerRepository {

    private val auth = FirebaseAuth.getInstance()
    private val db: FirebaseFirestore by lazy {
        Firebase.firestore
    }

    fun getRecommendation(): List<Examples> {
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

}