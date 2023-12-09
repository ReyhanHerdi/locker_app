package com.example.locker.data.repository

import com.example.locker.data.Examples
import com.example.locker.data.ExamplesData


class LockerRepository() {
    fun getRecommendation(): List<Examples> {
        return ExamplesData.examples

    }

}