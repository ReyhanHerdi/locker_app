package com.example.locker.screen.recomendation

import androidx.lifecycle.ViewModel
import com.example.locker.data.Examples
import com.example.locker.repositories.HomeRepository

class RecomendationViewModel(private val homeRepository: HomeRepository) : ViewModel() {

    fun getRecomendation(): List<Examples> {
        return homeRepository.getRecomendation()
    }
}