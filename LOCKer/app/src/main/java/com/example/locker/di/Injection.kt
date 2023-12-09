package com.example.locker.di

object Injection {
    object Injection {
        fun provideRepository(): HomeRepository {
            return HomeRepository()
        }
    }
}