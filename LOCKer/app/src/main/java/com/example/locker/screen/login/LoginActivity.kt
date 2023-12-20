package com.example.locker.screen.login

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.locker.R
import com.example.locker.databinding.ActivityLoginBinding
import com.example.locker.screen.MainActivity
import com.example.locker.screen.ViewModelFactory
import com.example.locker.screen.register.RegisterActivity
import com.example.locker.screen.welcome.WelcomeActivity
import com.google.android.material.snackbar.Snackbar

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
                authViewModel.login(email, password)
                authViewModel.loading.observe(this){
                    binding.progressBar.visibility = View.VISIBLE
                }
                showSnackbar(authViewModel.message.toString())
                navigate()
/*                showAlert(
                    resources.getString(R.string.Success),
                    resources.getString(R.string.SuccessMsg)
                ){_, _ ->
                    navigate()
                }*/
            } else {
                showSnackbar(resources.getString(R.string.empty_field))
            }

         //   startActivity(Intent(applicationContext, MainActivity::class.java))
        }

    }

    private fun showSnackbar(message: String?) {
        Snackbar.make(binding.root, message!!, Snackbar.LENGTH_SHORT).show()
    }

    private fun showAlert(
        title: String,
        message: String,
        onPositiveClick: ((dialog: DialogInterface, which: Int) -> Unit)? = null
    ) {
        AlertDialog.Builder(this).apply {
            setTitle(title)
            setMessage(message)
            setPositiveButton(resources.getString(R.string.next), onPositiveClick)
            create()
            show()
        }
    }

    private fun navigate(){
        val intent = Intent(applicationContext, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent)
    }
}