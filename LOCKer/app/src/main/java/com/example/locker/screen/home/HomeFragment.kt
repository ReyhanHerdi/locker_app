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
import com.bumptech.glide.Glide
import com.example.locker.R
import com.example.locker.data.model.Article
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
    private val listRecommendation = ArrayList<Article>()
    private val listNews = ArrayList<Article>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        homeViewModel.getRecommendation()
        Log.d("Data", listOf<Article>().toString())
        showRecommendation()
        showNews()

        binding.tvViewAllRecomendation.setOnClickListener(this)
        binding.tvViewAllNews.setOnClickListener(this)

        val url = "https://raw.githubusercontent.com/cheftz/capstone/main/Frame%2023-min%20(1).png"

        Glide.with(requireContext())
            .load(url)
            .into(binding.picHighlight)
            .clearOnDetach()

        homeViewModel.userData.observe(viewLifecycleOwner) { data ->
            if (data != null && data.username!!.isNotEmpty()) {
                binding.topBar.title = resources.getString(R.string.user, data.username)
            } else {
                binding.topBar.title = resources.getString(R.string.empty_name)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onResume() {
        super.onResume()
        homeViewModel.fetchData()
    }

    private fun getRecomendation(): ArrayList<Article> {
        val examples: List<Article> = homeViewModel.getRecommendation()
        for (i in examples) {
            listRecommendation.add(
                Article(
                    i.id,
                    i.title,
                    i.content,
                    i.image,
                    i.author
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
            override fun onItemClicked(article: Article) {
                Toast.makeText(context, "Not yet implemented", Toast.LENGTH_SHORT).show()
            }

        })
    }

    private fun getNews(): ArrayList<Article> {
        val examples: List<Article> = homeViewModel.getRecommendation()
        for (i in examples) {
            listNews.add(
                Article(
                    i.id,
                    i.title,
                    i.content,
                    i.image,
                    i.author

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