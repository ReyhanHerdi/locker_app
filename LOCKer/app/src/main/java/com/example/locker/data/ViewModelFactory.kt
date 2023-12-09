package com.example.locker.data

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.locker.screen.bookmark.BookmarkViewModel
import com.example.locker.screen.home.HomeViewModel
import com.example.locker.screen.login.AuthViewModel
import com.example.locker.screen.profile.ProfileViewModel
import com.example.locker.screen.scan.ScanViewModel
import com.example.locker.screen.search.SearchViewModel


class ViewModelFactory(/*private val repository:*/ ): ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T = when(modelClass) {

        HomeViewModel::class.java -> HomeViewModel()
        BookmarkViewModel::class.java -> BookmarkViewModel()
        ScanViewModel::class.java -> ScanViewModel()
        SearchViewModel::class.java -> SearchViewModel()
        ProfileViewModel::class.java -> ProfileViewModel()
        AuthViewModel::class.java -> AuthViewModel()

        else -> throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
    } as T

    companion object {
        @Volatile
        private var INSTANCE: ViewModelFactory? = null

        @JvmStatic
        fun getInstance(context: Context): ViewModelFactory {
            if (INSTANCE == null) {
                synchronized(ViewModelFactory::class.java){
                    INSTANCE = ViewModelFactory(/*Injection.provideRepository(context)*/)
                }
            }
            return INSTANCE as ViewModelFactory
        }
    }



}