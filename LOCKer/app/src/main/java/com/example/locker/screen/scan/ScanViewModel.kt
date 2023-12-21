package com.example.locker.screen.scan

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.locker.data.Result
import com.example.locker.data.repository.LockerRepository
import com.example.locker.data.response.Response
import kotlinx.coroutines.launch

class ScanViewModel(private val repository: LockerRepository) : ViewModel() {
    private val _result = MutableLiveData<Result<Response>>()
    val result: LiveData<Result<Response>> get() = _result

    fun predictJob(text: String){
        viewModelScope.launch {
            _result.value = Result.Loading
            _result.value = repository.predictJob(text)
        }
    }


}