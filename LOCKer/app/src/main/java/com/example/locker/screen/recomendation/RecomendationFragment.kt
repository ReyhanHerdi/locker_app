package com.example.locker.screen.recomendation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.locker.data.model.Article
import com.example.locker.databinding.FragmentRecomendationBinding
import com.example.locker.screen.ViewModelFactory
import com.example.locker.screen.adapter.RecommendationAdaper

class RecomendationFragment : Fragment() {

    private var _binding: FragmentRecomendationBinding? = null
    private val binding get() = _binding!!
    private val recomendationViewModel by viewModels<RecomendationViewModel> {
        ViewModelFactory.getInstance(requireContext())
    }
    private val listRecomendations = ArrayList<Article>()
    private lateinit var recommendationAllAdapter: RecommendationAdaper
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?):
            View {

        _binding = FragmentRecomendationBinding.inflate(inflater, container, false)
        val root: View = binding.root
        // Inflate the layout for this fragment
        showRecomendationAll()
        return root
    }

    private fun getRecomendationAll(): ArrayList<Article> {
        val examples: List<Article> = recomendationViewModel.getRecomendation()
        for (i in examples) {
            listRecomendations.add(
                Article(
                    i.id,
                    i.title,
                    i.content,
                    i.image,
                    i.author

                )
            )
        }
        return listRecomendations
    }

    private fun showRecomendationAll() {
        getRecomendationAll()
        val layoutManager = LinearLayoutManager(context)
        binding.rvRecomendationAll.layoutManager = layoutManager
        recommendationAllAdapter = RecommendationAdaper(listRecomendations, listRecomendations.size)
        binding.rvRecomendationAll.adapter = recommendationAllAdapter

        recommendationAllAdapter.setOnItemCallback(object : RecommendationAdaper.OnItemClickCallback {
            override fun onItemClicked(article: Article) {
                Toast.makeText(context, "Not yet implemented", Toast.LENGTH_SHORT).show()
            }

            override fun onBookmarkClicked(article: Article) {
                TODO("Not yet implemented")
            }

        })
    }
}