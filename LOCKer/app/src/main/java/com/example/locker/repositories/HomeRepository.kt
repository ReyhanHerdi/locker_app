package com.example.locker.repositories

class HomeRepository() {
    fun getRecomendation(): List<Examples> {
        return ExamplesData.examples

    }

}