package com.example.locker.screen.scan

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.NestedScrollView
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.locker.R
import com.example.locker.customview.ModalBottomSheet
import com.example.locker.data.ResultState
import com.example.locker.data.model.History
import com.example.locker.databinding.FragmentScanBinding
import com.example.locker.databinding.FragmentScanTextBinding
import com.example.locker.screen.ViewModelFactory
import kotlin.math.min

class ScanTextFragment : Fragment() {
    private var _binding: FragmentScanTextBinding? = null
    private val binding get() = _binding!!
    private val scanViewModel: ScanViewModel by viewModels {
        ViewModelFactory.getInstance(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentScanTextBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {

            btnStartScanning.setOnClickListener {
                scan()
            }

            tvClickSave.setOnClickListener {
                val job = binding.edtJobVacancy.text.toString()
                val result = binding.tvResult.text.toString()
                if (job.isNotEmpty() && result.isNotEmpty()){
                    save(job, result)
                    showToast(resources.getString(R.string.Success))
                } else {
                    showToast(resources.getString(R.string.empty_save))
                }
            }

        }
    }

    private fun scan() {
        val text = binding.edtJobVacancy.text.toString()
        if (text.isNotEmpty()) {
            scanViewModel.predictJob(text).observe(viewLifecycleOwner) { result ->
                if (result != null) {
                    when (result) {
                        is ResultState.Error -> {
                            showLoading(false)
                            showToast("error ${result.error}")
                        }

                        is ResultState.Success -> {
                            val resultScan = result.data
                            val getResult = resultScan.prediction?.get(0)?.get(0)
                            val percentage = getResult?.times(100.0)?.let { min(it, 100.0) }
                            val percentageFormat = String.format("%.2f", percentage)
                            if (percentageFormat.toDouble() >= 90.0) {
                                binding.tvResult.text = resources.getString(R.string.fraud)
                            } else {
                                binding.tvResult.text = resources.getString(R.string.real)
                            }
                            showLoading(false)
                        }

                        is ResultState.Loading -> showLoading(true)
                    }
                }
            }
        } else {
            showToast(resources.getString(R.string.empty_input))
        }

    }

    private fun save(text: String, result: String){
        scanViewModel.addHistory(History(text, result))
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun showToast(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }
}