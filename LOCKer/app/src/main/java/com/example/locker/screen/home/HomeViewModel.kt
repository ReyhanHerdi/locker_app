package com.example.locker.screen.home

import androidx.lifecycle.ViewModel
import com.example.locker.data.model.Examples
import com.example.locker.data.repository.LockerRepository

class HomeViewModel(private val homeRepository: LockerRepository) : ViewModel() {

    fun getRecommendation(): List<Examples> {
        return homeRepository.getRecommendation()
    }
}