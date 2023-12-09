package com.example.locker.screen.detail_news

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.locker.R
import com.example.locker.databinding.ActivityArticleDetailBinding

class ArticleDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityArticleDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityArticleDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.caret_left)
        supportActionBar?.title = "Article"

    }

    private fun newsContent(){
        binding.apply {
            tvArticleTitle.text = ""
            tvArticleAuthor.text = ""
            tvArticleContent.text = ""
            Glide.with(root.context)
                .load("")
                .into(ivArticle)
        }
    }
}