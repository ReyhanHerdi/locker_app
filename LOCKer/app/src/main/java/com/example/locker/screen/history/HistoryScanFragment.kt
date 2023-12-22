package com.example.locker.screen.history

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.locker.R
import com.example.locker.data.model.History
import com.example.locker.databinding.FragmentHistoryScanBinding
import com.example.locker.screen.ViewModelFactory
import com.example.locker.screen.adapter.HistoryAdapter

class HistoryScanFragment : Fragment() {
    private var _binding: FragmentHistoryScanBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: HistoryAdapter
    private val viewModel: HistoryViewModel by viewModels {
        ViewModelFactory.getInstance(requireContext())
    }
    private val historyList = ArrayList<History>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHistoryScanBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = HistoryAdapter(historyList)
        binding.apply {
            rvHistory.adapter = adapter
            rvHistory.layoutManager = LinearLayoutManager(requireContext())
            rvHistory.setHasFixedSize(true)
        }

        binding.btnBack.setOnClickListener {
            findNavController().navigate(R.id.action_historyScanFragment_to_navigation_scan)
        }
        showLoading(true)
    }

    override fun onResume() {
        super.onResume()
        getHistory()
    }

    private fun getHistory() {
        viewModel.getHistory()
        viewModel.scanHistory.observe(viewLifecycleOwner) { result ->
            showLoading(false)
            if (result.isEmpty()) {
                binding.tvEmptyHistory.visibility = View.VISIBLE
            } else {
                adapter.updateData(result)
            }
        }
    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

}