package com.example.locker.screen.history

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.locker.data.model.History
import com.example.locker.data.repository.LockerRepository
import kotlinx.coroutines.launch

class HistoryViewModel(private val repository: LockerRepository): ViewModel() {
    private val _scanHistory = MutableLiveData<List<History>>()
    val scanHistory: LiveData<List<History>> get() = _scanHistory

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> = _loading

    fun getHistory(){
        viewModelScope.launch {
            val data = repository.getScanHistory()
            _scanHistory.postValue(data)
        }
    }

}