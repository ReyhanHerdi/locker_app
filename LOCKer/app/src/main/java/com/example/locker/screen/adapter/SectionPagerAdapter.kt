package com.example.locker.screen.adapter

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.locker.screen.bookmark.BookmarkFragment
import com.example.locker.screen.bookmark.BookmarkedFragment
import com.example.locker.screen.scan.ScanFragment
import com.example.locker.screen.scan.ScanLabelFragment
import com.example.locker.screen.scan.ScanTextFragment

class SectionPagerAdapter(fragment: ScanFragment) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        var fragment: Fragment? = null
        when(position) {
            0 -> fragment = ScanLabelFragment()
            1 -> fragment = ScanTextFragment()
        }
        return fragment as Fragment
    }
}