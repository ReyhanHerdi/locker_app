package com.example.locker.screen.scan

import androidx.lifecycle.ViewModel
import com.example.locker.data.repository.LockerRepository

class ScanViewModel(private val repository: LockerRepository) : ViewModel() {
    fun predictJob(text: String) = repository.scanJob(text)

}