package com.example.locker.di

import android.app.Application
import android.content.Context
import com.example.locker.repositories.HomeRepository

object Injection {
    fun provideRepository(): HomeRepository {
        return HomeRepository()
    }
}