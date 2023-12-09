package com.example.locker.di

import android.content.Context
import com.example.locker.data.repository.LockerRepository

object Injection {
        fun provideRepository(context: Context): LockerRepository {
            return LockerRepository()
        }
}