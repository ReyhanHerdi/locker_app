package com.example.locker.screen.adapter

import android.annotation.SuppressLint
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.locker.data.local.database.BookmarkEntity
import com.example.locker.databinding.NewsListBinding
import com.example.locker.screen.article.ArticleDetailActivity

class BookmarkAdapter : ListAdapter<BookmarkEntity, BookmarkAdapter.MyViewHolder>(DIFF_CALLBACK) {
    inner class MyViewHolder(private val binding: NewsListBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(bookmark: BookmarkEntity){
            binding.apply {
                tvArticleTitle.text = bookmark.title
                tvNewsDescription.text = bookmark.content
                Glide.with(itemView.context)
                    .load(bookmark.image)
                    .into(imgArticle)
                    .clearOnDetach()

                itemView.setOnClickListener{
                    val intent = Intent(itemView.context, ArticleDetailActivity::class.java)
                    intent.putExtra(ArticleDetailActivity.KEY_DATA, bookmark)
                    itemView.context.startActivity(intent)
                }
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = NewsListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(getItem(position))
    }


    companion object {
        val DIFF_CALLBACK: DiffUtil.ItemCallback<BookmarkEntity> =
            object : DiffUtil.ItemCallback<BookmarkEntity>() {
                override fun areItemsTheSame(
                    oldItem: BookmarkEntity,
                    newItem: BookmarkEntity
                ): Boolean {
                    return oldItem.title == newItem.title
                }

                @SuppressLint("DiffUtilEquals")
                override fun areContentsTheSame(
                    oldItem: BookmarkEntity,
                    newItem: BookmarkEntity
                ): Boolean {
                    return oldItem == newItem
                }
            }
    }


}