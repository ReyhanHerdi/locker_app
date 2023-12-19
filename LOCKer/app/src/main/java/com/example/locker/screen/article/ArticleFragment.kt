package com.example.locker.screen.article

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.locker.data.model.Examples
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
    private val listNews = ArrayList<Examples>()

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

    private fun getNewsAll(): ArrayList<Examples> {
        val examples: List<Examples> = articleViewModel.getNews()
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

    private fun showNewsAll() {
        getNewsAll()
        val layoutManager = LinearLayoutManager(context)
        binding.rvNewsAll.layoutManager = layoutManager
        newsAllAdapter = ArticleAdapter(listNews, listNews.size)
        binding.rvNewsAll.adapter = newsAllAdapter

        newsAllAdapter.setOnItemClickCallback(object: ArticleAdapter.OnItemClickCallback {
            override fun onItemClicked(examples: Examples) {
                Toast.makeText(context, "Not Yet Implemented", Toast.LENGTH_SHORT).show()
            }

        })
    }
}