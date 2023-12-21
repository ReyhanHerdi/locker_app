package com.example.locker.screen.article

import androidx.lifecycle.ViewModel
import com.example.locker.data.local.database.BookmarkEntity
import com.example.locker.data.model.Article
import com.example.locker.data.repository.LockerRepository

class ArticleViewModel(private val homeRepository: LockerRepository): ViewModel() {
    fun getNews(): List<Article> {
        return homeRepository.getRecommendation()
    }

    fun insertBookmark(bookmark: BookmarkEntity) {
        homeRepository.setBookmark(bookmark)
    }

    fun deleteBookmark(id: Int) {
        homeRepository.deleteBookmark(id)
    }

    fun isBookmarked(id: Int): Boolean {
        return homeRepository.isBookmarked(id)
    }
}