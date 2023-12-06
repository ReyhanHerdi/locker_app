package com.example.locker.screen.welcome

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.locker.databinding.ActivityWelcomeBinding
import com.example.locker.screen.login.LoginActivity
import com.example.locker.screen.register.RegisterActivity

class WelcomeActivity : AppCompatActivity() {
    private val binding: ActivityWelcomeBinding by lazy {
        ActivityWelcomeBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.btnRegisterWelcome.setOnClickListener {
            startActivity(Intent(applicationContext, RegisterActivity::class.java))
        }

        binding.tvMoveLogin.setOnClickListener {
            startActivity(Intent(applicationContext, LoginActivity::class.java))
        }

    }
}