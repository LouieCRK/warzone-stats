package com.louie.warzonestats.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.louie.warzonestats.R
import com.louie.warzonestats.networking.services.LeaderboardKillsNetworkService
import com.louie.warzonestats.networking.services.LeaderboardWinsNetworkService

class LeaderboardFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val killsLeaderboard = LeaderboardKillsNetworkService.getLeaderboardKillsData()
        val winsLeaderboard = LeaderboardWinsNetworkService.getLeaderboardWinsData()

        val killsLeaderboardData = killsLeaderboard?.leaderboard?.data
        val winsLeaderboardData = winsLeaderboard?.leaderboard?.data

//        winsLeaderboardData.


        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_leaderboard, container, false)
    }

}