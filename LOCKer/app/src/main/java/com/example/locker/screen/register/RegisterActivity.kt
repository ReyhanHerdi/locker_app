package com.example.locker.screen.register

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.locker.R
import com.example.locker.data.model.User
import com.example.locker.databinding.ActivityRegisterBinding
import com.example.locker.screen.AuthActivity
import com.example.locker.screen.ViewModelFactory
import com.example.locker.screen.login.AuthViewModel
import com.example.locker.screen.login.LoginActivity
import com.example.locker.screen.welcome.WelcomeActivity
import com.example.locker.util.Reference.isEmailValid
import com.example.locker.util.Reference.isPasswordValid
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider

class RegisterActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var googleSignInClient: GoogleSignInClient

    private val binding: ActivityRegisterBinding by lazy {
        ActivityRegisterBinding.inflate(layoutInflater)
    }

    private val authViewModel: AuthViewModel by viewModels {
        ViewModelFactory.getInstance(applicationContext)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val gso = GoogleSignInOptions
            .Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()

        googleSignInClient = GoogleSignIn.getClient(this, gso)

        auth = FirebaseAuth.getInstance()

        binding.apply {
            btnBack.setOnClickListener {
                startActivity(Intent(applicationContext, WelcomeActivity::class.java))
            }

            tvMoveSignIn.setOnClickListener {
                startActivity(Intent(applicationContext, LoginActivity::class.java))
            }

            btnRegister.setOnClickListener {
                val username = etUsername.text.toString()
                val email = etEmail.text.toString()
                val password = etPassword.text.toString()

                if (username.isNotEmpty() && isEmailValid(applicationContext, email) && isPasswordValid(applicationContext, password)) {
                    authViewModel.register(
                        email = email,
                        pass = password,
                        User(username = username, email = email)
                    )
                    authViewModel.loading.observe(this@RegisterActivity){
                        showLoading(it)
                    }
                    showToast(authViewModel.message.toString())
                    finish()
                } else {
                    showToast(resources.getString(R.string.empty_field))
                }
            }

            btnGoogle.setOnClickListener {
                signInGoogle()
            }
        }

    }

    override fun onStart() {
        super.onStart()
        val currentUser = auth.currentUser
        updateUI(currentUser)
    }

    private fun signInGoogle() {
        val signInIntent = googleSignInClient.signInIntent
        resultLauncher.launch(signInIntent)
    }

    private var resultLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
            try {
                // success
                val account = task.getResult(ApiException::class.java)!!
                Log.d(TAG, "firebaseAuthWithGoogle:" + account.id)
                firebaseAuthWithGoogle(account.idToken!!)
            } catch (e: ApiException) {
                // failed
                showToast("Error : $e.toString()")
            }
        }
    }

    private fun firebaseAuthWithGoogle(idToken: String) {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        auth.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val user = auth.currentUser
                    updateUI(user)
                } else {
                    updateUI(null)
                }
            }
    }

    private fun updateUI(currentUser: FirebaseUser?) {
        if (currentUser != null) {
            startActivity(Intent(applicationContext, AuthActivity::class.java))
            finish()
        }
    }

    private fun showToast(messages: String?) {
        Toast.makeText(this, messages!!, Toast.LENGTH_SHORT).show()
    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

    companion object {
        private const val TAG = "LoginActivity"
    }

}