package com.example.locker.screen.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.locker.data.model.Article
import com.example.locker.data.repository.LockerRepository

class SearchViewModel(private val repository: LockerRepository): ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is search Fragment"
    }
    val text: LiveData<String> = _text

    fun getArticle(): List<Article> {
        return repository.getRecommendation()
    }
}