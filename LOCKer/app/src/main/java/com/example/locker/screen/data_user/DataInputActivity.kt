package com.example.locker.screen.data_user

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.locker.databinding.ActivityDataInputBinding

class DataInputActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDataInputBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDataInputBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }
}