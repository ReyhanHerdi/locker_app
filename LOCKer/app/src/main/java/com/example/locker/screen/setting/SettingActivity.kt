package com.example.locker.screen.setting

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.locker.R
import com.example.locker.databinding.ActivitySettingBinding

class SettingActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySettingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySettingBinding.inflate(layoutInflater)
        setContentView(binding.root)
        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.settings, SettingFragment())
                .commit()
        }
    }
}