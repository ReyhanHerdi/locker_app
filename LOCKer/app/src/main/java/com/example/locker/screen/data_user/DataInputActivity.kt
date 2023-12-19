package com.example.locker.screen.data_user

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.locker.R
import com.example.locker.data.model.User
import com.example.locker.databinding.ActivityDataInputBinding
import com.example.locker.screen.MainActivity
import com.example.locker.screen.ViewModelFactory

class DataInputActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDataInputBinding
    private val viewModel: DataInputViewModel by viewModels {
        ViewModelFactory.getInstance(applicationContext)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDataInputBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.topBar)

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_data_input, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean = when (item.itemId) {
        R.id.menu_save -> {
            // saveData()
            startActivity(Intent(this, MainActivity::class.java))
            finish()
            true
        }

        else -> super.onOptionsItemSelected(item)

    }

    private fun saveData() {
        val name = binding.edtName.text.toString()
        val email = binding.edtEmail.text.toString()
        val gender = binding.spinnerGender.selectedItem.toString()
        val location = binding.edtLocation.text.toString()
        val city = binding.edtCity.text.toString()
        val univOrSchool = binding.edtUnivOrSch.text.toString()
        val major = binding.edtMajor.text.toString()
        val skills = binding.edtSkill.text.toString()

        val data = User(
            username = name,
            email = email,
            gender = gender,
            location = location,
            city = city,
            univ = univOrSchool,
            major = major,
            skills = skills
        )

        viewModel.loading.observe(this) {
            showLoading(it)
        }
        viewModel.message.observe(this) { msg ->
            showToast(msg)
        }
        viewModel.save(data)
    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressIndicator.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

    private fun showToast(message: String) {
        Toast.makeText(applicationContext, message, Toast.LENGTH_SHORT).show()
    }

}