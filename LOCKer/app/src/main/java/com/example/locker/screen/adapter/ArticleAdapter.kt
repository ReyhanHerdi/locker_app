package com.example.locker.screen.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.locker.data.model.Article
import com.example.locker.databinding.NewsListBinding
import com.example.locker.screen.article.ArticleDetailActivity

class ArticleAdapter(private val listNews: List<Article>, private var dataCount: Int) : RecyclerView.Adapter<ArticleAdapter.NewsViewHolder>() {

    inner class NewsViewHolder(private val binding: NewsListBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(article: Article) {
            with(binding) {
                tvArticleTitle.text = article.title
                tvNewsDescription.text = article.content
                Glide.with(itemView.context)
                    .load(article.image)
                    .into(imgJob)
                    .clearOnDetach()

                imageButton.visibility = View.GONE

                itemView.setOnClickListener {
                    val intent = Intent(itemView.context, ArticleDetailActivity::class.java)
                    intent.putExtra(ArticleDetailActivity.KEY_DATA, article)
                    itemView.context.startActivity(intent)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val binding = NewsListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NewsViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return dataCount
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.bind(listNews[position])
    }
}