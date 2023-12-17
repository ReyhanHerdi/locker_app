package com.example.locker.screen.recomendation

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.locker.data.model.Examples
import com.example.locker.databinding.FragmentRecomendationBinding
import com.example.locker.screen.ViewModelFactory
import com.example.locker.screen.adapter.RecommendationAdaper
import com.example.locker.screen.detail_job.JobDetailsActivity

class RecomendationFragment : Fragment() {

    private var _binding: FragmentRecomendationBinding? = null
    private val binding get() = _binding!!
    private val recomendationViewModel by viewModels<RecomendationViewModel> {
        ViewModelFactory.getInstance(requireContext())
    }
    private val listRecomendations = ArrayList<Examples>()
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

    private fun getRecomendationAll(): ArrayList<Examples> {
        val examples: List<Examples> = recomendationViewModel.getRecomendation()
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

    private fun showRecomendationAll() {
        getRecomendationAll()
        val layoutManager = LinearLayoutManager(context)
        binding.rvRecomendationAll.layoutManager = layoutManager
        recommendationAllAdapter = RecommendationAdaper(listRecomendations, listRecomendations.size)
        binding.rvRecomendationAll.adapter = recommendationAllAdapter

        recommendationAllAdapter.setOnItemCallback(object : RecommendationAdaper.OnItemClickCallback {
            override fun onItemClicked(examples: Examples) {
                showClickedJob(examples)
            }

        })
    }

    private fun showClickedJob(article: Examples) {
        val intent = Intent(context, JobDetailsActivity::class.java )
        intent.putExtra(JobDetailsActivity.IMAGE, article.poster)
        startActivity(intent)
    }
}