package com.example.locker.screen.home

import androidx.lifecycle.ViewModel
import com.example.locker.data.Examples
import com.example.locker.repositories.HomeRepository

class HomeViewModel(private val homeRepository: HomeRepository) : ViewModel() {
    /*
    private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
    }
    val text: LiveData<String> = _text

     */

    fun getRecomendation(): List<Examples> {
        return homeRepository.getRecomendation()
    }
}