package com.example.locker.screen.scan

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.locker.data.model.History
import com.example.locker.data.model.Job
import com.example.locker.data.repository.LockerRepository
import kotlinx.coroutines.launch

class ScanViewModel(private val repository: LockerRepository) : ViewModel() {
    fun predictJob(text: String) = repository.scanJob(text)

    fun scanMultiple(data: String) = repository.multipleText(data)

    fun addHistory(history: History){
        viewModelScope.launch {
            repository.addScanHistory(history)
        }
    }

}