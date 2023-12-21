package com.example.locker.di

import android.content.Context
import com.example.locker.data.AppExecutors
import com.example.locker.data.local.database.BookmarkDatabase
import com.example.locker.data.repository.LockerRepository

object Injection {
    fun provideRepository(context: Context): LockerRepository {
        val database = BookmarkDatabase.getDatabase(context)
        val dao = database.bookmarkDao()
        val appExecutors = AppExecutors()
        return LockerRepository.getInstance(dao, appExecutors)
    }
}