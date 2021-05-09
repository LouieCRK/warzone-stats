package com.louie.warzonestats.networking.services

import com.google.gson.GsonBuilder
import com.louie.warzonestats.models.match.MatchModel
import okhttp3.*
import java.io.IOException
import java.util.concurrent.CountDownLatch

/**
 * This is a Singleton that allows the calling class instance access to a single instance of the
 * MatchNetworkService object.
 */
object MatchNetworkService {
    val client: OkHttpClient = OkHttpClient()

    fun getMatchData(username: String, platform: String) : MatchModel?{
        var result : MatchModel? = null
        val url = "https://app.wzstats.gg/v2/player/match?username=$username&platform=$platform"
        val request = Request.Builder().url(url).build()

        val countDownLatch = CountDownLatch(1)
        client.newCall(request).enqueue(object : Callback {
            override fun onResponse(call: Call?, response: Response?) {
                val body = response?.body()?.string()
                val gson = GsonBuilder().create()
                result = gson.fromJson(body, MatchModel::class.java)
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