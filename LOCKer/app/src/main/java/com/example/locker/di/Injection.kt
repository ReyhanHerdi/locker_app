package com.example.locker.di

import com.example.locker.repositories.HomeRepository

object Injection {
        fun provideRepository(): HomeRepository {
            return HomeRepository()
        }
}