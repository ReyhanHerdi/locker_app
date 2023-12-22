package com.example.locker.screen.adapter
import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.locker.R
import com.example.locker.data.local.database.BookmarkEntity
import com.example.locker.databinding.NewsListBinding

class BookmarkAdapter(private var bookmarkList: List<BookmarkEntity>) : RecyclerView.Adapter<BookmarkAdapter.NewsViewHolder>() {

    private lateinit var onItemClickCallback: OnItemClickCallback

    interface OnItemClickCallback {
        fun onItemClicked(bookmark: BookmarkEntity)
        fun onBookmarkClicked(bookmark: BookmarkEntity, position: Int)
    }

    inner class NewsViewHolder(private val binding: NewsListBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(bookmark: BookmarkEntity) {
            with(binding) {
                tvArticleTitle.text = bookmark.title
                tvNewsDescription.text = bookmark.content
                Glide.with(itemView.context)
                    .load(bookmark.image)
                    .into(imgJob)
                    .clearOnDetach()
                imageButton.setImageResource(R.drawable.bookmarked)

            }
        }

        val unBookmarkButton = binding.imageButton
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val binding = NewsListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NewsViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return bookmarkList.size
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.bind(bookmarkList[position])

        holder.itemView.setOnClickListener {
            onItemClickCallback.onItemClicked(bookmarkList[position])
        }

        holder.unBookmarkButton.setOnClickListener {
            onItemClickCallback.onBookmarkClicked(bookmarkList[position], position)
//            bookmarkList.remove(bookmarkList[position])
        }
    }

    fun setOnItemCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback

    }
}