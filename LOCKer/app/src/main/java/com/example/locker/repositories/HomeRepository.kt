package com.example.locker.repositories

import android.app.Application
import android.util.Log
import com.example.locker.data.Examples
import com.example.locker.data.ExamplesData

class HomeRepository() {
    fun getRecomendation(): List<Examples> {
        return ExamplesData.examples

    }

}