package com.example.locker.ui.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.locker.data.Examples
import com.example.locker.data.ExamplesData
import com.example.locker.databinding.JobListBinding

class RecomendationAdaper(private val listRecomendation: ArrayList<Examples>, var dataCount: Int) : RecyclerView.Adapter<RecomendationAdaper.RecomendationVewHolder>() {

    inner class RecomendationVewHolder(private val binding: JobListBinding) : RecyclerView.ViewHolder(binding.root) {

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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecomendationVewHolder {
        val binding = JobListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RecomendationVewHolder(binding)
    }

    override fun getItemCount(): Int {
        return dataCount
    }

    override fun onBindViewHolder(holder: RecomendationVewHolder, position: Int) {
        holder.bind(listRecomendation[position])

    }
}