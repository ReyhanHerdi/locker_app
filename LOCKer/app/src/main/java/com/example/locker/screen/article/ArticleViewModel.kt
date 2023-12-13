package com.example.locker.screen.article

import androidx.lifecycle.ViewModel
import com.example.locker.data.model.Examples
import com.example.locker.data.repository.LockerRepository

class ArticleViewModel(private val homeRepository: LockerRepository): ViewModel() {
    fun getNews(): List<Examples> {
        return homeRepository.getRecommendation()
    }
}