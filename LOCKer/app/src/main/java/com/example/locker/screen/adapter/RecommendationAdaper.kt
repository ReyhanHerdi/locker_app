package com.example.locker.screen.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.locker.data.model.Examples
import com.example.locker.databinding.JobListBinding

class RecommendationAdaper(private val listRecommendation: ArrayList<Examples>, var dataCount: Int) : RecyclerView.Adapter<RecommendationAdaper.RecommendationVewHolder>() {

    private lateinit var onItemClickCallback: OnItemClickCallback

    interface OnItemClickCallback {
        fun onItemClicked(examples: Examples)
    }

    inner class RecommendationVewHolder(private val binding: JobListBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(examples: Examples) {
            with(binding) {
                tvJobTitle.text = examples.judul
                Glide.with(itemView.context)
                    .load(examples.poster)
                    .into(imgJob)
                Log.d("Title", examples.judul)
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