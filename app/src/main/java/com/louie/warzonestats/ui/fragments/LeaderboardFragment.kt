package com.louie.warzonestats.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.louie.warzonestats.R
import com.louie.warzonestats.networking.services.LeaderboardKillsNetworkService
import com.louie.warzonestats.networking.services.LeaderboardWinsNetworkService

@Suppress("DEPRECATION")
class LeaderboardFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {
        // assign data to variables
        val killsLeaderboard = LeaderboardKillsNetworkService.getLeaderboardKillsData()
        val winsLeaderboard = LeaderboardWinsNetworkService.getLeaderboardWinsData()
        val killsLeaderboardData = killsLeaderboard?.leaderboard?.data
        val winsLeaderboardData = winsLeaderboard?.leaderboard?.data
        // inflate the layout for this fragment
        val inflatedLayout = inflater.inflate(R.layout.fragment_leaderboard, container, false)
        // assign variables to buttons
        val killsButton = inflatedLayout.findViewById<Button>(R.id.killsLB_button)
        val winsButton = inflatedLayout.findViewById<Button>(R.id.winsLB_button)

            // loop to assign data to kills leaderboard data to view
            for ((leaderboardIndex, player) in killsLeaderboardData!!.withIndex()) {
                // if index is larger than 10 leaderboards break..
                if (leaderboardIndex > 9) {
                    break
                }
                // concatenate resource id with index to string
                val killsDataString = "killsLB_kills_$leaderboardIndex"
                val userDataString = "killsLB_username_$leaderboardIndex"
                val platformDataString= "killsLB_platform_$leaderboardIndex"
                // use killsID to point to our view id
                val killsDataID = resources.getIdentifier(killsDataString, "id", activity?.packageName)
                val userDataID = resources.getIdentifier(userDataString, "id", activity?.packageName)
                val platformDataID = resources.getIdentifier(platformDataString, "id", activity?.packageName)
                // assign variable to our view object
                val killsData = inflatedLayout.findViewById<TextView>(killsDataID)
                val userData = inflatedLayout.findViewById<TextView>(userDataID)
                val platformData = inflatedLayout.findViewById<ImageView>(platformDataID)
                // set data to view text
                killsData.text = player.kills.toString()
                userData.text = player.username.toUpperCase()

                // case statement to set the drawable resource to the corresponding platform image
                when (player.user.platform == "battle"){
                    true -> platformData.setImageResource(R.drawable.ic_battle)
                }
                when (player.user.platform == "xbl"){
                    true -> platformData.setImageResource(R.drawable.ic_xbox)
                }
                when (player.user.platform == "psn"){
                    true -> platformData.setImageResource(R.drawable.ic_psn)
                }
            }

        // loop to assign data to wins leaderboard data to view
        for ((leaderboardIndex, player) in winsLeaderboardData!!.withIndex()) {
            // if index is larger than 10 leaderboards break..
            if (leaderboardIndex > 9) {
                break
            }
            // concatenate resource id with index to string
            val winsDataString = "winsLB_wins_$leaderboardIndex"
            val userDataString = "winsLB_username_$leaderboardIndex"
            val platformDataString= "winsLB_platform_$leaderboardIndex"
            // use killsID to point to our view id
            val winsDataID = resources.getIdentifier(winsDataString, "id", activity?.packageName)
            val userDataID = resources.getIdentifier(userDataString, "id", activity?.packageName)
            val platformDataID = resources.getIdentifier(platformDataString, "id", activity?.packageName)
            // assign variable to our view object
            val winsData = inflatedLayout.findViewById<TextView>(winsDataID)
            val userData = inflatedLayout.findViewById<TextView>(userDataID)
            val platformData = inflatedLayout.findViewById<ImageView>(platformDataID)
            // set data to view text
            winsData.text = player.count.toString()
            userData.text = player.username.toUpperCase()

            // case statement to set the drawable resource to the corresponding platform image
            when (player.user.platform == "battle"){
                true -> platformData.setImageResource(R.drawable.ic_battle)
            }
            when (player.user.platform == "xbl"){
                true -> platformData.setImageResource(R.drawable.ic_xbox)
            }
            when (player.user.platform == "psn"){
                true -> platformData.setImageResource(R.drawable.ic_psn)
            }
        }

        // leaderboard button listeners to change leaderboard type and button appearance
        killsButton.setOnClickListener {
            killsButton.setBackgroundColor(resources.getColor(R.color.grey_0))
            winsButton.setBackgroundColor(resources.getColor(R.color.grey_2))
            // iterate over index within leaderboard
            for ((leaderboardIndex, player) in killsLeaderboardData.withIndex()) {
                // if index is larger than 10 leaderboards break..
                if (leaderboardIndex > 9) {
                    break
                }
                // concatenate resource id with index to string
                val killsViewString = "killsView_$leaderboardIndex"
                val winsViewString = "winsView_$leaderboardIndex"
                // use killsID to point to our view id
                val killsViewID = resources.getIdentifier(killsViewString, "id", activity?.packageName)
                val winsViewID = resources.getIdentifier(winsViewString, "id", activity?.packageName)
                // assign variable to our view object
                val killsView = inflatedLayout.findViewById<View>(killsViewID)
                val winsView = inflatedLayout.findViewById<View>(winsViewID)
                // set the visibility of wins/kills data
                killsView.visibility = View.VISIBLE
                winsView.visibility = View.GONE
            }
        }

        winsButton.setOnClickListener {
            winsButton.setBackgroundColor(resources.getColor(R.color.grey_0))
            killsButton.setBackgroundColor(resources.getColor(R.color.grey_2))
            // iterate over index within leaderboard
            for ((leaderboardIndex, player) in winsLeaderboardData.withIndex()) {
                // if index is larger than 10 leaderboards break..
                if (leaderboardIndex > 9) {
                    break
                }
                // concatenate resource id with index to string
                val killsViewString = "killsView_$leaderboardIndex"
                val winsViewString = "winsView_$leaderboardIndex"
                // use killsID to point to our view id
                val killsID = resources.getIdentifier(killsViewString, "id", activity?.packageName)
                val winsID = resources.getIdentifier(winsViewString, "id", activity?.packageName)
                // assign variable to our view object
                val killsView = inflatedLayout.findViewById<View>(killsID)
                val winsView = inflatedLayout.findViewById<View>(winsID)
                // set the visibility of wins/kills data
                killsView.visibility = View.GONE
                winsView.visibility = View.VISIBLE
            }
        }

        // Inflate the layout for this fragment
        return inflatedLayout
    }

}