package com.example.locker.screen.bookmark

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.locker.R
import com.example.locker.data.local.database.BookmarkEntity
import com.example.locker.data.model.Article
import com.example.locker.databinding.FragmentBookmarkBinding
import com.example.locker.screen.ViewModelFactory
import com.example.locker.screen.adapter.ArticleAdapter
import com.example.locker.screen.adapter.BookmarkAdapter
import com.example.locker.screen.article.ArticleDetailActivity
import kotlinx.coroutines.launch

class BookmarkFragment : Fragment() {
    private var _binding: FragmentBookmarkBinding? = null
    private val binding get() = _binding!!

    private val bookmarkViewModel by viewModels<BookmarkViewModel> {
        ViewModelFactory.getInstance(requireContext())
    }

    private val listBookmark = ArrayList<BookmarkEntity>()
    private lateinit var adapter: BookmarkAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBookmarkBinding.inflate(inflater, container, false)

        val layoutManager = LinearLayoutManager(context)
        binding.rvBookmark.layoutManager = layoutManager
        setAdapter()

        return binding.root
    }

    private fun setAdapter() {
        bookmarkViewModel.getBookmark().observe(viewLifecycleOwner) { bookmarkList ->
            listBookmark.clear()
            if (bookmarkList != null) {
                listBookmark.addAll(bookmarkList)
                adapter = BookmarkAdapter(listBookmark)
                binding.rvBookmark.adapter = adapter
                Log.d("BOOKMARK", listBookmark.toString())

                adapter.setOnItemCallback(object : BookmarkAdapter.OnItemClickCallback {
                    override fun onItemClicked(bookmark: BookmarkEntity) {
                        val intent = Intent(context, ArticleDetailActivity::class.java)
                        intent.putExtra(ArticleDetailActivity.ID, bookmark.id.toString())
                        intent.putExtra(ArticleDetailActivity.TITLE, bookmark.title)
                        intent.putExtra(ArticleDetailActivity.AUTHOR, bookmark.author)
                        intent.putExtra(ArticleDetailActivity.CONTENT, bookmark.content)
                        intent.putExtra(ArticleDetailActivity.IMAGE, bookmark.image)
                        startActivity(intent)
                    }

                    override fun onBookmarkClicked(bookmark: BookmarkEntity, position: Int) {
                        listBookmark.remove(bookmark)
                        adapter.notifyItemRemoved(position)
                        adapter.notifyItemRangeChanged(position, listBookmark.size)
                        bookmarkViewModel.deleteBookmark(bookmark.id)

                        if (listBookmark.isEmpty()) {
                            binding.ivNothinInActivity.visibility = View.VISIBLE
                            binding.tvNothingBookmarked.visibility = View.VISIBLE
                        }
                    }

                })
            }

            if (bookmarkList.isNotEmpty()) {
                binding.ivNothinInActivity.visibility = View.GONE
                binding.tvNothingBookmarked.visibility = View.GONE
            }
        }
    }

//    private fun showAdapter() {
//        setAdapter()
//        Log.d("SHOW BOOKMARK", setAdapter().toString())
//        val layoutManager = LinearLayoutManager(context)
//    }

    companion object {
        @StringRes
        private val TAB_TITLES = intArrayOf(
            R.string.applied,
            R.string.saved
        )
    }
}