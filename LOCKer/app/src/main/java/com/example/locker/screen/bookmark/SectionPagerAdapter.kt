package com.example.locker.screen.bookmark

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class SectionPagerAdapter(fragment: BookmarkFragment) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        val cFragment = BookmarkedFragment()
        cFragment.arguments = Bundle().apply {
            putInt(BookmarkedFragment.ARG_SECTION_NUMBER, position + 1)
        }
        return cFragment
    }
}