package com.example.locker.screen.data_user

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.locker.databinding.FragmentDataInputBinding


class DataInputFragment : Fragment() {
    private var _binding: FragmentDataInputBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentDataInputBinding.inflate(inflater, container, false)

        return binding.root
    }

}