package com.example.locker.screen.article

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.IntentCompat.getParcelableExtra
import com.bumptech.glide.Glide
import com.example.locker.R
import com.example.locker.data.model.Article
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

        val article = getParcelableExtra(intent, KEY_DATA, Article::class.java)

        binding.apply {
            tvArticleTitle.text = article?.title
            tvArticleAuthor.text = article?.author
            tvArticleContent.text = article?.content
            Glide.with(root.context)
                .load(article?.image)
                .into(ivArticle)
        }
    }

    companion object {
        const val KEY_DATA = "key_data"
    }
}