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
}