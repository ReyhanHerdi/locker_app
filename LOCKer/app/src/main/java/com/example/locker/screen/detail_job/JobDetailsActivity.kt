package com.example.locker.screen.detail_job

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.example.locker.R
import com.example.locker.databinding.ActivityJobDetailsBinding

class JobDetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityJobDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityJobDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.topBar)
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setHomeAsUpIndicator(R.drawable.caret_left)
        }

        saveIcon(true)
    }

    private fun setJobDetails(){
        binding.apply {
            tvJobPosition.text = ""
            tvCompany.text = ""
            tvLocation.text = ""
            tvJobType.text = ""
            tvJobPlace.text = ""
            tvJobMinimumReq.text = ""
            tvSalary.text = ""
            tvRequirements.text = ""
            jobDescription.text = ""
            Glide.with(root.context)
                .load("")
                .into(ivCompany)
        }
    }

    private fun saveIcon(save: Boolean){
        binding.btnSave.setImageDrawable(
            if (save){
                ContextCompat.getDrawable(
                    applicationContext,
                    R.drawable.bookmarked
                )
            } else {
                ContextCompat.getDrawable(
                    applicationContext,
                    R.drawable.bookmark
                )
            }
        )
    }
}