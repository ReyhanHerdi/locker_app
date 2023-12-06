package com.example.locker.screen.recomendation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.locker.data.Examples
import com.example.locker.databinding.ActivityRecomendationBinding
import com.example.locker.screen.ViewModelFactory
import com.example.locker.ui.adapter.RecomendationAdaper

class RecomendationActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRecomendationBinding
    private val recomendationViewModel by viewModels<RecomendationViewModel> {
        ViewModelFactory.getInstance()
    }
    private val listRecomendations = ArrayList<Examples>()
    private lateinit var recomendationAllAdapter: RecomendationAdaper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecomendationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        showRecomendationAll()
    }

    private fun getRecomendationAll(): ArrayList<Examples> {
        val examples: List<Examples> = recomendationViewModel.getRecomendation()
        for (i in examples) {
            listRecomendations.add(
                Examples(
                    i.id,
                    i.judul,
                    i.sinopsis,
                    i.tahunRilis,
                    i.poster

                )
            )
        }
        return listRecomendations
    }

    private fun showRecomendationAll() {
        getRecomendationAll()
        val layoutManager = LinearLayoutManager(this)
        binding.rvRecomendationAll.layoutManager = layoutManager
        recomendationAllAdapter = RecomendationAdaper(listRecomendations, listRecomendations.size)
        binding.rvRecomendationAll.adapter = recomendationAllAdapter
    }
}