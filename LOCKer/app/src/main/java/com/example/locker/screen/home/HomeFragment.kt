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
import com.example.locker.screen.ExploreActivity
import com.example.locker.data.Examples
import com.example.locker.databinding.FragmentHomeBinding
import com.example.locker.screen.ViewModelFactory
import com.example.locker.ui.adapter.NewsAdapter
import com.example.locker.ui.adapter.RecomendationAdaper

class HomeFragment : Fragment(), View.OnClickListener {

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

        binding.tvViewAllRecomendation.setOnClickListener(this)
        binding.tvViewAllNews.setOnClickListener(this)
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

        recomendationAdaper = RecomendationAdaper(listRecomendation, 3)
        binding.rvRecomendation.adapter = recomendationAdaper

        recomendationAdaper.setOnItemCallback(object : RecomendationAdaper.OnItemClickCallback {
            override fun onItemClicked(examples: Examples) {
                Toast.makeText(context, "Not yet implemented", Toast.LENGTH_SHORT).show()
            }

        })
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
        newsAdapter = NewsAdapter(listNews, 3)
        binding.rvNews.adapter = newsAdapter

        newsAdapter.setOnItemClickCallback(object : NewsAdapter.OnItemClickCallback {
            override fun onItemClicked(examples: Examples) {
                Toast.makeText(context, "Not yet implemented", Toast.LENGTH_SHORT).show()
            }

        })


    }

    override fun onClick(view: View?) {
        when(view) {
            binding.tvViewAllRecomendation -> {
                val intent = Intent(requireActivity(), ExploreActivity::class.java)
                intent.putExtra(ExploreActivity.FRAGMENT, "recomendation")
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