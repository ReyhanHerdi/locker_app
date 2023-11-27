package com.example.locker.screen.register

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.locker.databinding.ActivityRegisterBinding
import com.example.locker.screen.login.LoginActivity
import com.example.locker.screen.welcome.WelcomeActivity

class RegisterActivity : AppCompatActivity() {
    private val binding: ActivityRegisterBinding by lazy {
        ActivityRegisterBinding.inflate(layoutInflater)
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
            startActivity(Intent(applicationContext, LoginActivity::class.java)) }
    }
}