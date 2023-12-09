package com.example.locker.di

import com.example.locker.data.repository.LockerRepository

object Injection {
        fun provideRepository(): LockerRepository {
            return LockerRepository()
        }
}