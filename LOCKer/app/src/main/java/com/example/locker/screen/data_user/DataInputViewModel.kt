package com.example.locker.screen.data_user

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.locker.data.model.User
import com.example.locker.data.repository.LockerRepository

class DataInputViewModel(private val repository: LockerRepository): ViewModel() {

    private val _message = MutableLiveData<String>()
    val message: LiveData<String> = _message

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> = _loading


    private val _userData = MutableLiveData<User>()
    val userData: LiveData<User> get() = _userData

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> get() = _error

    fun save(userData: User){
        _loading.value = true
        repository.saveData(userData){_, it ->
            if (it==null){
                _loading.value = false
                _message.value = "Save Data Success"
            } else {
                _loading.value = false
                _message.value = it.cause?.message ?: it.message ?: "Failed Save data"
            }
        }
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
}