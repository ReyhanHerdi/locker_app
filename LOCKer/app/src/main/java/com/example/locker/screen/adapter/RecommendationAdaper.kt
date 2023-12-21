package com.example.locker.screen.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.locker.data.model.Article
import com.example.locker.databinding.JobListBinding

class RecommendationAdaper(private val listRecommendation: ArrayList<Article>, var dataCount: Int) : RecyclerView.Adapter<RecommendationAdaper.RecommendationVewHolder>() {

    private lateinit var onItemClickCallback: OnItemClickCallback

    interface OnItemClickCallback {
        fun onItemClicked(article: Article)
    }

    inner class RecommendationVewHolder(private val binding: JobListBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(article: Article) {
            with(binding) {
                tvJobTitle.text = article.title
                Glide.with(itemView.context)
                    .load(article.image)
                    .into(imgArticle)
                Log.d("Title", article.title)
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