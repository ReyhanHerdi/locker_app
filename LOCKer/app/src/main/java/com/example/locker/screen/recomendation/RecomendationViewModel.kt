package com.example.locker.screen.recomendation

import androidx.lifecycle.ViewModel
import com.example.locker.data.model.Examples
import com.example.locker.data.repository.LockerRepository

class RecomendationViewModel(private val homeRepository: LockerRepository) : ViewModel() {

    fun getRecomendation(): List<Examples> {
        return homeRepository.getRecommendation()
    }
}