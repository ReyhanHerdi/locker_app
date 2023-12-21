package com.example.locker.screen.article

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.locker.data.model.Article
import com.example.locker.databinding.FragmentNewsBinding
import com.example.locker.screen.ViewModelFactory
import com.example.locker.screen.adapter.ArticleAdapter

class ArticleFragment : Fragment() {

    private var _binding: FragmentNewsBinding? = null
    private val binding get() = _binding!!
    private val articleViewModel: ArticleViewModel by viewModels {
        ViewModelFactory.getInstance(requireContext())
    }
    private lateinit var newsAllAdapter: ArticleAdapter
    private val listNews = ArrayList<Article>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentNewsBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        showNewsAll()
    }

    private fun getNewsAll(): ArrayList<Article> {
        val examples: List<Article> = articleViewModel.getNews()
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

    private fun showNewsAll() {
        getNewsAll()
        val layoutManager = LinearLayoutManager(context)
        binding.rvNewsAll.layoutManager = layoutManager
        newsAllAdapter = ArticleAdapter(listNews, listNews.size)
        binding.rvNewsAll.adapter = newsAllAdapter
    }
}