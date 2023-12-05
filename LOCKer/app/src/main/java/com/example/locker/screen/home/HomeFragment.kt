package com.example.locker.screen.home

import android.app.Application
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.locker.data.Examples
import com.example.locker.databinding.FragmentHomeBinding
import com.example.locker.screen.ViewModelFactory
import com.example.locker.ui.adapter.NewsAdapter
import com.example.locker.ui.adapter.RecomendationAdaper

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var recomendationAdaper: RecomendationAdaper
    private lateinit var newsAdapter: NewsAdapter
    private val homeViewModel by viewModels<HomeViewModel> {
        ViewModelFactory.getInstance()
    }
    private val listRecomendation = ArrayList<Examples>()
    private val listNews = ArrayList<Examples>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root
        /*
        val textView: TextView = binding.tvNews
        homeViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }

         */
        homeViewModel.getRecomendation()
        Log.d("Data", listOf<Examples>().toString())
        showRecomendation()
        showNews()
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun getRecomendation(): ArrayList<Examples> {
        val examples: List<Examples> = homeViewModel.getRecomendation()
        for (i in examples) {
            listRecomendation.add(
                Examples(
                    i.id,
                    i.judul,
                    i.sinopsis,
                    i.tahunRilis,
                    i.poster
                )
            )
        }
        return listRecomendation
    }

    private fun showRecomendation() {
        getRecomendation()
        val layoutManager = LinearLayoutManager(context)
        binding.rvRecomendation.layoutManager = layoutManager

        recomendationAdaper = RecomendationAdaper(listRecomendation)
        binding.rvRecomendation.adapter = recomendationAdaper
    }

    private fun getNews(): ArrayList<Examples> {
        val examples: List<Examples> = homeViewModel.getRecomendation()
        for (i in examples) {
            listNews.add(
                Examples(
                    i.id,
                    i.judul,
                    i.sinopsis,
                    i.tahunRilis,
                    i.poster

                )
            )
        }
        return listNews
    }

    private fun showNews() {
        getNews()
        val layoutManager = LinearLayoutManager(context)
        binding.rvNews.layoutManager = layoutManager
        newsAdapter = NewsAdapter(listNews)
        binding.rvNews.adapter = newsAdapter


    }
}