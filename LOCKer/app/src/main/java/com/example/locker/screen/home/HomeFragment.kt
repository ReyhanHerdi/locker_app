package com.example.locker.screen.home

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.locker.data.model.Examples
import com.example.locker.databinding.FragmentHomeBinding
import com.example.locker.screen.ViewModelFactory
import com.example.locker.screen.adapter.ArticleAdapter
import com.example.locker.screen.adapter.RecommendationAdaper
import com.example.locker.screen.explore.ExploreActivity

class HomeFragment : Fragment(), View.OnClickListener {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var recommendationAdaper: RecommendationAdaper
    private lateinit var articleAdapter: ArticleAdapter
    private val homeViewModel by viewModels<HomeViewModel> {
        ViewModelFactory.getInstance(requireContext())
    }
    private val listRecommendation = ArrayList<Examples>()
    private val listNews = ArrayList<Examples>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        homeViewModel.getRecommendation()
        Log.d("Data", listOf<Examples>().toString())
        showRecommendation()
        showNews()

        binding.tvViewAllRecomendation.setOnClickListener(this)
        binding.tvViewAllNews.setOnClickListener(this)
        return root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.topBar.title = "Hi Andi"
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun getRecomendation(): ArrayList<Examples> {
        val examples: List<Examples> = homeViewModel.getRecommendation()
        for (i in examples) {
            listRecommendation.add(
                Examples(
                    i.id,
                    i.judul,
                    i.sinopsis,
                    i.tahunRilis,
                    i.poster
                )
            )
        }
        return listRecommendation
    }

    private fun showRecommendation() {
        getRecomendation()
        val layoutManager = LinearLayoutManager(context)
        binding.rvRecomendation.layoutManager = layoutManager

        recommendationAdaper = RecommendationAdaper(listRecommendation, 3)
        binding.rvRecomendation.adapter = recommendationAdaper

        recommendationAdaper.setOnItemCallback(object : RecommendationAdaper.OnItemClickCallback {
            override fun onItemClicked(examples: Examples) {
                Toast.makeText(context, "Not yet implemented", Toast.LENGTH_SHORT).show()
            }

        })
    }

    private fun getNews(): ArrayList<Examples> {
        val examples: List<Examples> = homeViewModel.getRecommendation()
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
        articleAdapter = ArticleAdapter(listNews, 5)
        binding.rvNews.adapter = articleAdapter

        articleAdapter.setOnItemClickCallback(object : ArticleAdapter.OnItemClickCallback {
            override fun onItemClicked(examples: Examples) {
                Toast.makeText(context, "Not yet implemented", Toast.LENGTH_SHORT).show()
            }

        })

    }

    override fun onClick(view: View?) {
        when (view) {
            binding.tvViewAllRecomendation -> {
                val intent = Intent(requireActivity(), ExploreActivity::class.java)
                intent.putExtra(ExploreActivity.FRAGMENT, "recommendation")
                startActivity(intent)
            }
            binding.tvViewAllNews -> {
                val intent = Intent(requireActivity(), ExploreActivity::class.java)
                intent.putExtra(ExploreActivity.FRAGMENT, "news")
                startActivity(intent)
            }
        }
    }
}