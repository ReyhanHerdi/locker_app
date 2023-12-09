package com.example.locker.screen.login

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.locker.databinding.ActivityLoginBinding
import com.example.locker.screen.MainActivity
import com.example.locker.screen.register.RegisterActivity
import com.example.locker.screen.welcome.WelcomeActivity
import com.google.android.material.snackbar.Snackbar

class LoginActivity : AppCompatActivity() {

    private val binding: ActivityLoginBinding by lazy {
        ActivityLoginBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.btnBack.setOnClickListener {
            startActivity(Intent(applicationContext, WelcomeActivity::class.java))
        }

        binding.tvMoveRegister.setOnClickListener {
            startActivity(Intent(applicationContext, RegisterActivity::class.java))
        }

        binding.btnLogin.setOnClickListener {
            val email = binding.etEmail.text.toString()
            val password = binding.etPassword.text.toString()

            if(email.isNotEmpty() && password.isNotEmpty()){

            } else {
                showSnackbar("Please Fill Everything")
            }

            startActivity(Intent(applicationContext, MainActivity::class.java))
        }

    }

    private fun showSnackbar(message: String?) {
        Snackbar.make(binding.root, message!!, Snackbar.LENGTH_SHORT).show()
    }
}