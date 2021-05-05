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

                println(playerStats.data)
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

// todo - implement correct class method to filter needed data
class Data(val data: Object)


// todo - remove json paste
// todo - HOW TO ACCESS?? - data > lifetime > mode > br > properties, need kills, kdRatio, wins etc...

//    {
//        "uno": "8a5a5f4d-af77-4d60-8eac-fb77fa",
//        "data": {
//            "uno": "crook#21832",
//            "level": 100,
//            "weekly": {
//            "lifetime": {
//                "all": {
//                "mode": {
//                    "br": {
//                        "properties": {
//                            "cash": 0,
//                            "wins": 240,
//                            "downs": 14500,
//                            "kills": 13904,
//                            "score": 12747345,
//                            "deaths": 6736,
//                            "tokens": 0,
//                            "topTen": 1016,
//                            "kdRatio": 2.0641330166270784,
//                            "revives": 1451,
//                            "topFive": 658,
//                            "contracts": 2944,
//                            "timePlayed": 2804539,
//                            "gamesPlayed": 2434,
//                            "topTwentyFive": 1798,
//                            "scorePerMinute": 272.7153018731421
