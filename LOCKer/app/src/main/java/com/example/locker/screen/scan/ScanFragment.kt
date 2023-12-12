package com.example.locker.screen.scan

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.locker.databinding.FragmentScanBinding
import com.example.locker.screen.detail_job.JobDetailsActivity

class ScanFragment : Fragment() {

    private var _binding: FragmentScanBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val scanViewModel =
            ViewModelProvider(this)[ScanViewModel::class.java]

        _binding = FragmentScanBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textNotifications
        scanViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }

        binding.btnMove.setOnClickListener {
            startActivity(Intent(requireActivity(), JobDetailsActivity::class.java))
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}