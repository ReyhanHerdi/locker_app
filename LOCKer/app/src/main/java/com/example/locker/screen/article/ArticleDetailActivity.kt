package com.example.locker.screen.article

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.IntentCompat.getParcelableExtra
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.locker.R
import com.example.locker.data.local.database.BookmarkEntity
import com.example.locker.data.model.Article
import com.example.locker.databinding.ActivityArticleDetailBinding
import com.example.locker.screen.ViewModelFactory

class ArticleDetailActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityArticleDetailBinding
    private lateinit var articleViewModel: ArticleViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityArticleDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.caret_left)
        supportActionBar?.title = "Article"

        val factory = ViewModelFactory.getInstance(this)
        articleViewModel = ViewModelProvider(this, factory).get(ArticleViewModel::class.java)

        val article = getParcelableExtra(intent, KEY_DATA, Article::class.java)
        if (article != null) {
            binding.apply {
                tvArticleTitle.text = article.title
                tvArticleAuthor.text = article.author
                tvArticleContent.text = article.content
                Glide.with(root.context)
                    .load(article.image)
                    .into(ivArticle)
            }
            isBookmarked(article.id.toInt())

        } else {
            binding.apply {
                tvArticleTitle.text = intent.getStringExtra(TITLE)
                tvArticleAuthor.text = intent.getStringExtra(AUTHOR)
                tvArticleContent.text = intent.getStringExtra(CONTENT)
                Glide.with(root.context)
                    .load(intent.getStringExtra(IMAGE))
                    .into(ivArticle)

                fabBookmark.visibility = View.GONE
            }
            isBookmarked(intent.getStringExtra(ID)!!.toInt())
        }

        binding.fabBookmark.setOnClickListener(this)
    }

    companion object {
        const val KEY_DATA = "key_data"
        const val ID = "id"
        const val TITLE = "title"
        const val AUTHOR = "author"
        const val CONTENT = "cntent"
        const val IMAGE = "image"
    }

    override fun onClick(view: View?) {
        when(view) {
            binding.fabBookmark -> {
                isBookmarkedInsert()
            }
        }
    }

    private fun isBookmarkedInsert() {
        val article = getParcelableExtra(intent, KEY_DATA, Article::class.java)
        if (!articleViewModel.isBookmarked(article?.id!!.toInt())) {
            try {
                articleViewModel.insertBookmark(BookmarkEntity(
                    article.id.toInt(),
                    article.title,
                    article.content,
                    article.author,
                    article.image,
                    true
                ))
                binding.fabBookmark.setImageResource(R.drawable.bookmarked)
            } catch (e: Exception) {
                Log.d("EXCEPTION", e.toString())
            }
        } else {
            articleViewModel.deleteBookmark(article.id.toInt())
            binding.fabBookmark.setImageResource(R.drawable.bookmark)
        }
    }

    private fun isBookmarked(id: Int) {
        if (articleViewModel.isBookmarked(id)) {
            binding.fabBookmark.setImageResource(R.drawable.bookmarked)
        } else {
            binding.fabBookmark.setImageResource(R.drawable.bookmark)
        }
    }
}