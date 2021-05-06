package com.louie.warzonestats

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.gson.GsonBuilder
import com.louie.warzonestats.fragments.FaveFragment
import com.louie.warzonestats.fragments.HomeFragment
import com.louie.warzonestats.fragments.LeaderboardFragment
import com.louie.warzonestats.fragments.StreamerFragment
import okhttp3.*
import java.io.IOException

open class MainActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.setContentView(R.layout.activity_main)
        // removes generic title bar
        this.supportActionBar?.hide()

        // todo - searchButton returns NULL, reason is because my searchButton is within 'HomeFragment' Unsure how to resolve
        // todo - purpose of this is to change to 'SecondActivity' when user clicks 'searchButton'
        // variable in reference to searchButton within homepage
        val searchButton = findViewById<Button>(R.id.searchButton)
        // use searchButton and set a listener for user clicks

        searchButton.setOnClickListener{
            // set the intent to show SecondActivity
            val intent = Intent(this, SecondActivity::class.java)

            startActivity(intent)
        }

        // assign fragments to variables
        val homeFragment = HomeFragment()
        val leaderboardFragment = LeaderboardFragment()
        val streamerFragment = StreamerFragment()
        val faveFragment = FaveFragment()
        // initiate with home fragment
        makeCurrentFragment(homeFragment)

        val bottomNavigation =
            findViewById<com.google.android.material.bottomnavigation.BottomNavigationView>(R.id.bottom_navigation)

        bottomNavigation.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.ic_home -> makeCurrentFragment(homeFragment)
                R.id.ic_leaderboard -> makeCurrentFragment(leaderboardFragment)
                R.id.ic_streamer -> makeCurrentFragment(streamerFragment)
                R.id.ic_fave -> makeCurrentFragment(faveFragment)
            }
            true
        }
        fetchJson()
    }

    private fun fetchJson() {
        // todo - username and platform within the URL need to be generated via user input
        // todo - username string - '#' should be removed & replaced with '%23' in-order for battle.net usernames to be in correct format
        val url = "https://app.wzstats.gg/v2/player?username=crook%2321832&platform=battle"

        val request = Request.Builder().url(url).build()

        val client = OkHttpClient()

        client.newCall(request).enqueue(object : Callback {
            override fun onResponse(call: Call?, response: Response?) {

                val body = response?.body()?.string()
                val gson = GsonBuilder().create()
                val playerStats = gson.fromJson(body, Data::class.java)

                println(playerStats.data.toString())
                // thought this would work but doesn't
//                playerStats.data.lifetime.mode.br.properties
            }

            override fun onFailure(call: Call?, e: IOException?) {
                println("Failed API Request")
            }
        })
    }

    private fun makeCurrentFragment(fragment: Fragment) =
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.nav_wrapper, fragment)
            commit()
        }
}

// todo - class needs to be in the correct format to get usable object???
class Data(val data: Object)
