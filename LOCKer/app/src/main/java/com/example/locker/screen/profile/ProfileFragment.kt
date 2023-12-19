package com.example.locker.screen.profile

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.locker.R
import com.example.locker.databinding.FragmentProfileBinding
import com.example.locker.screen.ViewModelFactory
import com.example.locker.screen.data_user.DataInputActivity
import com.example.locker.screen.login.AuthViewModel
import com.example.locker.screen.setting.SettingActivity

class ProfileFragment : Fragment(), View.OnClickListener {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    private lateinit var profileViewModel: ProfileViewModel
    private val viewModel: AuthViewModel by viewModels {
        ViewModelFactory.getInstance(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnEditProfile.setOnClickListener(this)

        binding.btnLogout.setOnClickListener(this)
        binding.topBar.setOnMenuItemClickListener {menuItem ->
            when(menuItem.itemId){
                R.id.menu_setting -> {
                    startActivity(Intent(requireContext(), SettingActivity::class.java))
                    true
                }
                else -> false
            }
        }

    }

    override fun onClick(view: View?) {
        when (view) {
            binding.btnEditProfile -> startActivity(Intent(requireActivity(), DataInputActivity::class.java))
            binding.btnLogout -> /*viewModel.logout()*/ Toast.makeText(requireContext(), "Logout", Toast.LENGTH_SHORT).show()

        }
    }


}