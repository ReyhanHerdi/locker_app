package com.example.locker.screen.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.locker.data.model.User
import com.example.locker.data.repository.LockerRepository

class ProfileViewModel(private val repository: LockerRepository): ViewModel() {

    private val _userData = MutableLiveData<User>()
    val userData: LiveData<User> get() = _userData

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> get() = _error

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> = _loading

    fun logout() = repository.logout()

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

}