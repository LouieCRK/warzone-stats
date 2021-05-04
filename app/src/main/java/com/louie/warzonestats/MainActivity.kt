package com.louie.warzonestats

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.louie.warzonestats.fragments.FaveFragment
import com.louie.warzonestats.fragments.HomeFragment
import com.louie.warzonestats.fragments.LeaderboardFragment
import com.louie.warzonestats.fragments.StreamerFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val homeFragment = HomeFragment()
        val leaderboardFragment = LeaderboardFragment()
        val streamerFragment = StreamerFragment()
        val faveFragment = FaveFragment()

        makeCurrentFragment(homeFragment)

        val bottomNavigation = findViewById<com.google.android.material.bottomnavigation.BottomNavigationView>(R.id.bottom_navigation)

        bottomNavigation.setOnNavigationItemReselectedListener {
            when (it.itemId){
                R.id.ic_home -> makeCurrentFragment(homeFragment)
                R.id.ic_leaderboard -> makeCurrentFragment(leaderboardFragment)
                R.id.ic_streamer -> makeCurrentFragment(streamerFragment)
                R.id.ic_fave -> makeCurrentFragment(faveFragment)
            }
            true
        }

    }

    private fun makeCurrentFragment(fragment: Fragment) =
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.nav_wrapper, fragment)
            commit()
        }
}