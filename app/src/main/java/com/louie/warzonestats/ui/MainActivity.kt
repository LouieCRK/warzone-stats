package com.louie.warzonestats.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.louie.warzonestats.R
import com.louie.warzonestats.ui.fragments.FaveFragment
import com.louie.warzonestats.ui.fragments.HomeFragment
import com.louie.warzonestats.ui.fragments.LeaderboardFragment
import com.louie.warzonestats.ui.profile.ProfileActivity

open class MainActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.setContentView(R.layout.activity_main)

        // removes generic title bar
        this.supportActionBar?.hide()

        // assign fragments to variables
        val homeFragment = HomeFragment()
        val leaderboardFragment = LeaderboardFragment()
        val faveFragment = FaveFragment()

        // initiate with home fragment
        makeCurrentFragment(homeFragment)

        val bottomNavigation =
            findViewById<com.google.android.material.bottomnavigation.BottomNavigationView>(R.id.bottom_navigation)

        bottomNavigation.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.ic_home -> makeCurrentFragment(homeFragment)
                R.id.ic_leaderboard -> makeCurrentFragment(leaderboardFragment)
                R.id.ic_fave -> makeCurrentFragment(faveFragment)
            }
            true
        }
    }

    fun navigateToPlayerProfile(){
        val intent = Intent(this, ProfileActivity::class.java).apply { }
        startActivity(intent)
    }

    private fun makeCurrentFragment(fragment: Fragment) =
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.nav_wrapper, fragment)
            commit()
        }
}