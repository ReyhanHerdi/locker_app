package com.example.locker.screen.bookmark

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.locker.data.local.database.BookmarkEntity
import com.example.locker.data.repository.LockerRepository

class BookmarkViewModel(private val repository: LockerRepository) : ViewModel() {
        fun getBookmark() : LiveData<List<BookmarkEntity>> = repository.getBookmark()

        fun deleteBookmark(title: Int) {
                repository.deleteBookmark(title)
        }
}