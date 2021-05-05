package com.louie.warzonestats

import android.os.Bundle
import android.view.Window
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.gson.GsonBuilder
import com.louie.warzonestats.fragments.FaveFragment
import com.louie.warzonestats.fragments.HomeFragment
import com.louie.warzonestats.fragments.LeaderboardFragment
import com.louie.warzonestats.fragments.StreamerFragment
import okhttp3.*
import java.io.IOException

open class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //Remove title bar
        this.requestWindowFeature(Window.FEATURE_NO_TITLE)
        this.setContentView(R.layout.activity_main)

        val homeFragment = HomeFragment()
        val leaderboardFragment = LeaderboardFragment()
        val streamerFragment = StreamerFragment()
        val faveFragment = FaveFragment()

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

    fun fetchJson() {
        println("Attempt API fetch")

        val url = "https://app.wzstats.gg/v2/player?username=crook%2321832&platform=battle"
        val request = Request.Builder().url(url).build()
        val client = OkHttpClient()
        client.newCall(request).enqueue(object : Callback {
            override fun onResponse(call: Call?, response: Response?) {
                val body = response?.body()?.string()
                println(body)

                val gson = GsonBuilder().create()

                val Data = gson.fromJson(body, Data::class.java)
            }

            override fun onFailure(call: Call?, e: IOException?) {
                println("Failed API Request crook")
            }
        })
    }

    private fun makeCurrentFragment(fragment: Fragment) =
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.nav_wrapper, fragment)
            commit()
        }
}
    // todo - implement correct class method to filter needed data
    class Data(val uno: String, val level: Int)


