package com.example.locker.screen.recomendation

import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import com.example.locker.data.Examples
import com.example.locker.data.ExamplesData
import com.example.locker.repositories.HomeRepository
import com.example.locker.screen.ViewModelFactory
import com.example.locker.screen.home.HomeViewModel

class RecomendationViewModel(private val homeRepository: HomeRepository) : ViewModel() {

    fun getRecomendation(): List<Examples> {
        return homeRepository.getRecomendation()
    }
}