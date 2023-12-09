package com.example.locker.screen.recomendation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.locker.data.Examples
import com.example.locker.databinding.FragmentRecomendationBinding
import com.example.locker.screen.ViewModelFactory
import com.example.locker.ui.adapter.RecomendationAdaper

class RecomendationFragment : Fragment() {

    private var _binding: FragmentRecomendationBinding? = null
    private val binding get() = _binding!!
    private val recomendationViewModel by viewModels<RecomendationViewModel> {
        ViewModelFactory.getInstance()
    }
    private val listRecomendations = ArrayList<Examples>()
    private lateinit var recomendationAllAdapter: RecomendationAdaper
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
        recomendationAllAdapter = RecomendationAdaper(listRecomendations, listRecomendations.size)
        binding.rvRecomendationAll.adapter = recomendationAllAdapter

        recomendationAllAdapter.setOnItemCallback(object : RecomendationAdaper.OnItemClickCallback {
            override fun onItemClicked(examples: Examples) {
                Toast.makeText(context, "Not yet implemented", Toast.LENGTH_SHORT).show()
            }

        })
    }
}