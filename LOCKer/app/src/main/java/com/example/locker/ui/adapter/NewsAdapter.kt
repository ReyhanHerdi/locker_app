package com.example.locker.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.locker.data.Examples
import com.example.locker.databinding.NewsListBinding

class NewsAdapter(private val listNews: ArrayList<Examples>) : RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {
    inner class NewsViewHolder(private val binding: NewsListBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(examples: Examples) {
            with(binding) {
                tvNewsTitle.text = examples.judul
                tvNewsDescription.text = examples.sinopsis
                Glide.with(itemView.context)
                    .load(examples.poster)
                    .into(imgJob)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val binding = NewsListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NewsViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return 3
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.bind(listNews[position])
    }

}