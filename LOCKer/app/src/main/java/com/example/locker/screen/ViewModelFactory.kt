package com.example.locker.screen

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.locker.data.repository.LockerRepository
import com.example.locker.di.Injection
import com.example.locker.screen.article.ArticleViewModel
import com.example.locker.screen.bookmark.BookmarkViewModel
import com.example.locker.screen.data_user.DataInputViewModel
import com.example.locker.screen.history.HistoryViewModel
import com.example.locker.screen.home.HomeViewModel
import com.example.locker.screen.login.AuthViewModel
import com.example.locker.screen.profile.ProfileViewModel
import com.example.locker.screen.recomendation.RecomendationViewModel
import com.example.locker.screen.scan.ScanViewModel
import com.example.locker.screen.search.SearchViewModel

@Suppress("UNCHECKED_CAST")
class ViewModelFactory(private val repository: LockerRepository) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T = when(modelClass){

        HomeViewModel::class.java -> HomeViewModel(repository)
        RecomendationViewModel::class.java -> RecomendationViewModel(repository)
        ArticleViewModel::class.java -> ArticleViewModel(repository)
        AuthViewModel::class.java -> AuthViewModel(repository)
        SearchViewModel::class.java -> SearchViewModel(repository)
        DataInputViewModel::class.java -> DataInputViewModel(repository)
        ProfileViewModel::class.java -> ProfileViewModel(repository)
        BookmarkViewModel::class.java -> BookmarkViewModel(repository)
        HistoryViewModel::class.java -> HistoryViewModel(repository)
        ScanViewModel::class.java -> ScanViewModel(repository)

        else ->  throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)

    } as T

    companion object {
        @Volatile
        private var INSTANCE: ViewModelFactory? = null

        @JvmStatic
        fun getInstance(context: Context): ViewModelFactory {
            if (INSTANCE == null) {
                synchronized(ViewModelFactory::class.java) {
                    INSTANCE = ViewModelFactory(Injection.provideRepository(context))
                }
            }
            return INSTANCE as ViewModelFactory
        }
    }
}