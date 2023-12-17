package com.example.locker.screen.article

import android.content.Intent
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

        articleContent()

    }

    private fun articleContent(){
        binding.apply {
            tvArticleTitle.text = intent.getStringExtra(TITLE)
            tvArticleAuthor.text = ""
            tvArticleContent.text = intent.getStringExtra(DESCRIPTION)
            Glide.with(root.context)
                .load(intent.getStringExtra(IMAGE))
                .into(ivArticle)
        }
    }

    companion object {
        const val TITLE = "title"
        const val IMAGE = "image"
        const val DESCRIPTION = "description"
    }
}