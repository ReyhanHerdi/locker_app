package com.example.locker.screen.register

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.locker.R
import com.example.locker.data.model.User
import com.example.locker.databinding.ActivityRegisterBinding
import com.example.locker.screen.ViewModelFactory
import com.example.locker.screen.login.AuthViewModel
import com.example.locker.screen.login.LoginActivity
import com.example.locker.screen.welcome.WelcomeActivity
import com.google.android.material.snackbar.Snackbar

class RegisterActivity : AppCompatActivity() {
    private val binding: ActivityRegisterBinding by lazy {
        ActivityRegisterBinding.inflate(layoutInflater)
    }

    private val authViewModel: AuthViewModel by viewModels {
        ViewModelFactory.getInstance(applicationContext)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.btnBack.setOnClickListener {
            startActivity(Intent(applicationContext, WelcomeActivity::class.java))
        }

        binding.tvMoveSignIn.setOnClickListener {
            startActivity(Intent(applicationContext, LoginActivity::class.java))
        }

        binding.btnRegister.setOnClickListener {
            val username = binding.etUsername.text.toString()
            val email = binding.etEmail.text.toString()
            val password = binding.etPassword.text.toString()

            if (username.isNotEmpty() && email.isNotEmpty() && password.isNotEmpty()) {
                authViewModel.register(
                    email = email,
                    pass = password,
                    User(username = username, email = email)
                )
                showSnackbar(authViewModel.message.toString())
                finish()
            } else {
                showSnackbar(resources.getString(R.string.empty_field))
            }

        //    startActivity(Intent(applicationContext, LoginActivity::class.java))
        }
    }

    private fun showSnackbar(messages: String?) {
        Snackbar.make(binding.root, messages!!, Snackbar.LENGTH_SHORT).show()
    }
}