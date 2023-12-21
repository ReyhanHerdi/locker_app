package com.example.locker.screen.login

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.locker.R
import com.example.locker.databinding.ActivityLoginBinding
import com.example.locker.screen.AuthActivity
import com.example.locker.screen.ViewModelFactory
import com.example.locker.screen.register.RegisterActivity
import com.example.locker.screen.welcome.WelcomeActivity

class LoginActivity : AppCompatActivity() {

    private val binding: ActivityLoginBinding by lazy {
        ActivityLoginBinding.inflate(layoutInflater)
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

        binding.tvMoveRegister.setOnClickListener {
            startActivity(Intent(applicationContext, RegisterActivity::class.java))
        }

        binding.btnLogin.setOnClickListener {
            val email = binding.etEmail.text.toString()
            val password = binding.etPassword.text.toString()

            if(email.isNotEmpty() && password.isNotEmpty()){
                authViewModel.loading.observe(this){
                    binding.progressBar.visibility = View.VISIBLE
                }
                authViewModel.login(email, password)
                showToast(authViewModel.message.toString())
                navigate()

            } else {
                showToast(resources.getString(R.string.empty_field))
            }
        }

    }

    private fun showToast(message: String?) {
        Toast.makeText(this, message!!, Toast.LENGTH_SHORT).show()
    }

    private fun navigate(){
        val intent = Intent(applicationContext, AuthActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent)
    }
}