package com.example.locker.screen.bookmark

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.locker.R

class BookmarkedFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_bookmarked, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val tvLabel: TextView = view.findViewById(R.id.hello)
        val index = arguments?.getInt(ARG_SECTION_NUMBER, 0)

        if (index == 1) {
            tvLabel.text = getString(R.string.you_have_not, "apply")
        } else {
            tvLabel.text = getString(R.string.you_have_not, "save")
        }

    }

    companion object {
        const val ARG_SECTION_NUMBER = "section_number"
    }
}