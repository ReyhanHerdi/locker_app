package com.example.locker.screen.scan

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.NestedScrollView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.locker.R
import com.example.locker.customview.ModalBottomSheet
import com.example.locker.data.Result
import com.example.locker.databinding.FragmentScanBinding
import com.example.locker.screen.ViewModelFactory

class ScanFragment : Fragment() {

    private var _binding: FragmentScanBinding? = null
    private val binding get() = _binding!!
    private val scanViewModel: ScanViewModel by viewModels {
        ViewModelFactory.getInstance(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentScanBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            nestedScrollView.setOnScrollChangeListener(NestedScrollView.OnScrollChangeListener { _, _, scrollY, _, oldScrollY ->
                if (scrollY > oldScrollY + 10 && fabTutorial.isExtended) {
                    fabTutorial.shrink()
                }

                if (scrollY < oldScrollY - 10 && fabTutorial.isExtended) {
                    fabTutorial.extend()
                }

                if (scrollY == 0) {
                    fabTutorial.extend()
                }
            })

            topBar.setOnMenuItemClickListener { menuItem ->
                when (menuItem.itemId) {
                    R.id.menu_history -> {
                        findNavController().navigate(R.id.action_navigation_scan_to_historyScanFragment)
                        true
                    }

                    else -> false
                }
            }

        }

        binding.apply {

            fabTutorial.setOnClickListener {
                val modalBottomSheet = ModalBottomSheet()
                childFragmentManager.let { modalBottomSheet.show(it, ModalBottomSheet.TAG) }
            }

        }

        binding.btnStartScanning.setOnClickListener{
            val text = binding.edtJobVacancy.text.toString()
            scanViewModel.predictJob(text)
        }

        scanViewModel.result.observe(viewLifecycleOwner){ result ->
            when(result){
                is Result.Loading -> showLoading(true)
                is Result.Success -> {
                    val hasil = result.data.toString()
                    if (hasil.isNotEmpty()){
                        binding.tvResult.text = hasil
                        Log.d("ScanFragment", hasil)
                    }
                }
                is Result.Failure -> {
                    val error = result.exception.toString()
                    showToast(error)
                    binding.textView.text = error
                    Log.d("ScanFragment", error)
                }
            }
        }

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