package com.example.locker.screen.news

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.locker.data.Examples
import com.example.locker.databinding.ActivityNewsBinding
import com.example.locker.screen.ViewModelFactory
import com.example.locker.ui.adapter.NewsAdapter

class NewsActivity : AppCompatActivity() {

    private lateinit var  binding: ActivityNewsBinding
    private val newsViewModel by viewModels<NewsViewModel> {
        ViewModelFactory.getInstance()
    }
    private lateinit var newsAllAdapter: NewsAdapter
    private val listRecomendations = ArrayList<Examples>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        showNewsAll()
    }

    private fun getNewsAll(): ArrayList<Examples> {
        val examples: List<Examples> = newsViewModel.getNews()
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

    private fun showNewsAll() {
        getNewsAll()
        val layoutManager = LinearLayoutManager(this)
        binding.rvNewsAll.layoutManager = layoutManager
        newsAllAdapter = NewsAdapter(listRecomendations, listRecomendations.size)
        binding.rvNewsAll.adapter = newsAllAdapter
    }
}