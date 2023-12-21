package com.example.locker.screen.bookmark

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

    private fun setAdapter(): ArrayList<BookmarkEntity> {
        bookmarkViewModel.getBookmark().observe(viewLifecycleOwner) { bookmarkList ->
            if (bookmarkList != null) {
                listBookmark.addAll(bookmarkList)
                adapter = BookmarkAdapter(listBookmark)
                binding.rvBookmark.adapter = adapter
                Log.d("BOOKMARK", listBookmark.toString())
            }

            adapter.setOnItemCallback(object : BookmarkAdapter.OnItemClickCallback {
                override fun onItemClicked(bookmark: BookmarkEntity) {
                    Toast.makeText(context, bookmark.title, Toast.LENGTH_SHORT).show()
                }

                override fun onBookmarkClicked(bookmark: BookmarkEntity) {
                    bookmarkViewModel.deleteBookmark(bookmark.id)

                }

            })
        }

        return listBookmark
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