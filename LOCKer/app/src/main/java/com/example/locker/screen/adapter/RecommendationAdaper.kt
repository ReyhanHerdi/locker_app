package com.example.locker.screen.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.locker.R
import com.example.locker.data.local.database.BookmarkEntity
import com.example.locker.data.model.Article
import com.example.locker.databinding.JobListBinding
import com.example.locker.screen.ViewModelFactory
import com.example.locker.screen.bookmark.BookmarkViewModel

class RecommendationAdaper(
    private val listRecommendation: ArrayList<Article>,
    var dataCount: Int,
) : RecyclerView.Adapter<RecommendationAdaper.RecommendationVewHolder>() {

    private lateinit var onItemClickCallback: OnItemClickCallback
    val bookmarkList: ArrayList<BookmarkEntity>? = null

    interface OnItemClickCallback {
        fun onItemClicked(article: Article)
        fun onBookmarkClicked(article: Article)
    }

    inner class RecommendationVewHolder(
        private val binding: JobListBinding,
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(article: Article) {
            with(binding) {
                tvJobTitle.text = article.title
                Glide.with(itemView.context)
                    .load(article.image)
                    .into(imgArticle)

                binding.imageButton.setOnClickListener {
                    imageButton.setImageResource(R.drawable.bookmarked)
                    onItemClickCallback.onBookmarkClicked(article)
                    Log.d("ADAPTER BOOKMARK", bookmarkList.toString())
                }

                Log.d("LIST RECOMENDATION", listRecommendation.toString())
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecommendationVewHolder {
        val binding = JobListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RecommendationVewHolder(binding)
    }

    override fun getItemCount(): Int {
        return dataCount
    }

    override fun onBindViewHolder(holder: RecommendationVewHolder, position: Int) {
        holder.bind(listRecommendation[position])

        holder.itemView.setOnClickListener {
            onItemClickCallback.onItemClicked(listRecommendation[position])
        }

    }

    fun setOnItemCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }
}