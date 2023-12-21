package com.example.locker.screen.bookmark

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.locker.data.local.database.BookmarkEntity
import com.example.locker.data.repository.LockerRepository
import kotlinx.coroutines.launch

class BookmarkViewModel(private val repository: LockerRepository) : ViewModel() {

    fun getBookmarkedArticle() = repository.getBookmark()

    fun bookmarkArticle(bookmark: BookmarkEntity){
        viewModelScope.launch {
            repository.setBookmark(bookmark, true)
        }
    }

    fun deleteBookmark(title: String){
        viewModelScope.launch {
            repository.deleteBookmark(title)
        }
    }

}