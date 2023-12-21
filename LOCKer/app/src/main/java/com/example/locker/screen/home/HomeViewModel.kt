package com.example.locker.screen.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.locker.data.local.database.BookmarkEntity
import com.example.locker.data.model.Article
import com.example.locker.data.model.User
import com.example.locker.data.repository.LockerRepository

class HomeViewModel(private val repository: LockerRepository) : ViewModel() {

    private val _userData = MutableLiveData<User>()
    val userData: LiveData<User> get() = _userData

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> get() = _error

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> = _loading


    fun getRecommendation(): List<Article> {
        return repository.getRecommendation()
    }

    fun fetchData() {
        repository.getUserData{ user, exception ->
            if (exception != null) {
                _error.value = "Failed to fetch data: ${exception.message}"
                _loading.value = true
            } else {
                _userData.value = user
                _loading.value = false
            }
        }
    }

    fun isBookmarked(id: Int): Boolean {
        return repository.isBookmarked(id)
    }

    fun insertBookmark(bookmark: BookmarkEntity) {
        repository.setBookmark(bookmark)
    }

    fun deleteBookmark(id: Int) {
        repository.deleteBookmark(id)
    }
}