package com.example.locker.screen.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.locker.databinding.FragmentProfileBinding

class ProfileFragment : Fragment(), View.OnClickListener {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: ProfileViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)

        binding.btnEditProfile.setOnClickListener(this)

        return binding.root
    }

    override fun onClick(view: View?) {
        when(view) {
            binding.btnEditProfile -> {
                Toast.makeText(context, "Not yet implemented", Toast.LENGTH_SHORT).show()
            }
        }
    }

}