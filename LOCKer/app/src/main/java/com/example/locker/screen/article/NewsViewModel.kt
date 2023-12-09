package com.example.locker.screen.article

import androidx.lifecycle.ViewModel
import com.example.locker.data.Examples
import com.example.locker.data.repository.LockerRepository

class NewsViewModel(private val homeRepository: LockerRepository): ViewModel() {
    fun getNews(): List<Examples> {
        return homeRepository.getRecommendation()
    }
}