package com.example.locker.screen.data_user

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.example.locker.R
import com.example.locker.databinding.ActivityDataInputBinding
import com.example.locker.screen.MainActivity

class DataInputActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDataInputBinding

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

    override fun onOptionsItemSelected(item: MenuItem): Boolean = when(item.itemId){

        R.id.menu_save -> {
            /* TODO */
            startActivity(Intent(this, MainActivity::class.java))
            finish()
            true
        }

        else -> super.onOptionsItemSelected(item)

    }

    private fun saveData(){
        val name = binding.edtName.text.toString()
        val email = binding.edtEmail.text.toString()
        val gender = binding.spinnerGender.selectedItem.toString()
        val location = binding.edtLocation.text.toString()
        val city = binding.edtCity.text.toString()
        val univOrSchool = binding.edtUnivOrSch.text.toString()
        val major = binding.edtMajor.text.toString()
        val skills = binding.edtSkill.text.toString()
    }

}