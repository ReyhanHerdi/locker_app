package com.example.locker.screen

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.locker.R
import com.example.locker.databinding.ActivityExploreBinding
import com.example.locker.screen.article.NewsFragment
import com.example.locker.screen.recomendation.RecomendationFragment

class ExploreActivity : AppCompatActivity() {

    private lateinit var binding: ActivityExploreBinding
    private lateinit var fragmentScreen: String
    private lateinit var screen: Fragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityExploreBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val fragment = intent.getStringExtra(FRAGMENT)
        val fragmentManager = supportFragmentManager

        if (fragment == "recommendation") {
            fragmentScreen = RecomendationFragment::class.java.simpleName
            binding.topBar.title = "Explore Jobs"
            screen =  RecomendationFragment()
        } else {
            fragmentScreen = NewsFragment::class.java.simpleName
            binding.topBar.title = "Explore News"
            screen = NewsFragment()
        }
        //val screen = fragmentManager.findFragmentByTag(fragmentScreen)
        fragmentManager
            .beginTransaction()
            .add(R.id.explore_frame_container, screen, fragmentScreen)
            .commit()
    }

    companion object {
        const val FRAGMENT = "fragment"
    }
}