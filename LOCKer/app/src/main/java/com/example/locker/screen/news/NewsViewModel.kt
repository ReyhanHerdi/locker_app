package com.example.locker.screen.news

import androidx.lifecycle.ViewModel
import com.example.locker.data.Examples
import com.example.locker.repositories.HomeRepository

class NewsViewModel(private val homeRepository: HomeRepository): ViewModel() {
    fun getNews(): List<Examples> {
        return homeRepository.getRecomendation()
    }
}