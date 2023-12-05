package com.example.locker.screen.detail_job

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.locker.databinding.ActivityJobDetailsBinding

class JobDetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityJobDetailsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityJobDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}