package com.example.locker.screen.result

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.IntentCompat.getParcelableExtra
import com.example.locker.data.model.History
import com.example.locker.databinding.FragmentResultBinding
import com.example.locker.screen.article.ArticleDetailActivity.Companion.KEY_DATA

class ResultFragment : AppCompatActivity() {
    private lateinit var binding: FragmentResultBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val detail = getParcelableExtra(intent, KEY_DATA, History::class.java)

        binding.edtResult.setText(detail?.jobText)
        binding.tvResult.text = detail?.resultScan

        binding.btnBack.setOnClickListener {
            navigateToScanActivity()
        }
    }

    private fun navigateToScanActivity() {
    }

    companion object{
        const val KEY_DETAIL = "detail"
    }
}