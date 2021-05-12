package com.louie.warzonestats.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
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

        // todo - implement for leaderboard buttons
        // leaderboard type button listeners to change leaderboard type and button appearance
        killsButton.setOnClickListener {
            killsButton.setBackgroundColor(resources.getColor(R.color.grey_0))
            winsButton.setBackgroundColor(resources.getColor(R.color.grey_2))

            // iterate over index within leaderboard
            for ((leaderboardIndex, player) in killsLeaderboardData!!.withIndex()) {

                // if index is larger than 10 leaderboards break..
                if (leaderboardIndex > 9) {
                    break
                }

                // concatenate resource id with index to string
                val viewID = "killsView_$leaderboardIndex"
                // use viewID to point to our view id
                val resID = resources.getIdentifier(viewID, "id", activity?.packageName)
                // assign variable to our view object
                val killsView = inflatedLayout.findViewById<View>(resID)
                killsView.visibility = View.VISIBLE


            }
        }
        winsButton.setOnClickListener {
            winsButton.setBackgroundColor(resources.getColor(R.color.grey_0))
            killsButton.setBackgroundColor(resources.getColor(R.color.grey_2))

            for ((leaderboardIndex, player) in killsLeaderboardData!!.withIndex()) {

                // if index is larger than 10 leaderboards break..
                if (leaderboardIndex > 9) {
                    break
                }

                val viewID = "killsView_$leaderboardIndex"
                val resID = resources.getIdentifier(viewID, "id", activity?.packageName)
                val killsView = inflatedLayout.findViewById<View>(resID)
                killsView.visibility = View.GONE



            }
        }


        // Inflate the layout for this fragment
        return inflatedLayout
    }

}