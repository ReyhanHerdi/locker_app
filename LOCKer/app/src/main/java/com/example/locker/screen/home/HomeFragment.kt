package com.example.locker.screen.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.locker.R
import com.example.locker.data.model.Article
import com.example.locker.databinding.FragmentHomeBinding
import com.example.locker.screen.ViewModelFactory
import com.example.locker.screen.adapter.ArticleAdapter

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var articleAdapter: ArticleAdapter
    private val homeViewModel by viewModels<HomeViewModel> {
        ViewModelFactory.getInstance(requireContext())
    }
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
        showNews()

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
}