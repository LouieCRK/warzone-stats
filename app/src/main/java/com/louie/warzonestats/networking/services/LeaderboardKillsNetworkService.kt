package com.louie.warzonestats.networking.services

import com.google.gson.GsonBuilder
import com.louie.warzonestats.models.leaderboard.kills.LeaderboardKillsModel
import okhttp3.*
import java.io.IOException
import java.util.concurrent.CountDownLatch

/**
 * This is a Singleton that allows the calling class instance access to a single instance of the
 * MatchNetworkService object.
 */
object LeaderboardKillsNetworkService {
    // assign client variable to OkHttpClient
    val client: OkHttpClient = OkHttpClient()

    // function to get leaderboard kills & wins data
    fun getLeaderboardKillsData() : LeaderboardKillsModel?{
        var result : LeaderboardKillsModel? = null

        val url = "https://app.wzstats.gg/leaderboard/?leaderboardType=0&leaderboardRangeType=0&leaderboardCategory=0"
        val request = Request.Builder().url(url).build()

        val countDownLatch = CountDownLatch(1)
        client.newCall(request).enqueue(object : Callback {
            override fun onResponse(call: Call?, response: Response?) {
                val body = response?.body()?.string()
                val gson = GsonBuilder().create()
                result = gson.fromJson(body, LeaderboardKillsModel::class.java)
                countDownLatch.countDown()

            }

            override fun onFailure(call: Call?, e: IOException?) {
                println("\n\n\n Failed API Request \n\n\n")
                countDownLatch.countDown()
            }
        })
        countDownLatch.await()
        return result
    }
}