package com.example.locker.screen.article

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.content.IntentCompat.getParcelableExtra
import com.bumptech.glide.Glide
import com.example.locker.R
import com.example.locker.data.model.Article
import com.example.locker.databinding.ActivityArticleDetailBinding

class ArticleDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityArticleDetailBinding

    private var image = ""

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

        binding.fabBookmark.setOnClickListener {

        }
    }

    private fun bookmarkIcon(save: Boolean){
        binding.fabBookmark.setImageDrawable(
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

    companion object {
        const val KEY_DATA = "key_data"
    }
}