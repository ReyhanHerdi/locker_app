package com.example.locker.screen.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.locker.data.model.Article
import com.example.locker.databinding.FragmentSearchBinding
import com.example.locker.screen.ViewModelFactory
import com.example.locker.screen.adapter.ArticleAdapter

class SearchFragment : Fragment() {

    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!
    private val searchViewModel: SearchViewModel by viewModels {
        ViewModelFactory.getInstance(requireContext())
    }

    private val articleList = ArrayList<Article>()
    private lateinit var adapter: ArticleAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)

        showAdapter("")
        searchData()

        return binding.root
    }

    private fun getData(): ArrayList<Article> {
        val examples: List<Article> = searchViewModel.getArticle()
        for (i in examples) {
            articleList.add(
                Article(
                    i.id,
                    i.title,
                    i.content,
                    i.image,
                    i.author

                )
            )
        }
        return articleList
    }

    private fun showAdapter(filter: String) {
        val filteredArticleList = getData().filter { it.title.contains(filter, ignoreCase = true) }
        val uniqueAritcleList = filteredArticleList.distinctBy { it.id }
        val layoutManager = LinearLayoutManager(context)
        binding.rvSearch.layoutManager = layoutManager
        adapter = ArticleAdapter(uniqueAritcleList, uniqueAritcleList.size)
        binding.rvSearch.adapter = adapter

    }

    private fun searchData() {
        with(binding) {
            searchView.setupWithSearchBar(searchBar)
            searchView
                .editText
                .setOnEditorActionListener { _, _, _ ->
                    searchBar.hint = searchView.text
                    showAdapter(searchView.text.toString())
                    searchView.hide()
                    false
                }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}