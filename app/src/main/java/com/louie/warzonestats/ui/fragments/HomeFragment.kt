package com.louie.warzonestats.ui.fragments

import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.android.material.textfield.TextInputEditText
import com.louie.warzonestats.R
import com.louie.warzonestats.networking.services.MatchNetworkService
import com.louie.warzonestats.networking.services.PlayerNetworkService
import com.louie.warzonestats.services.MatchService
import com.louie.warzonestats.services.PlayerService
import com.louie.warzonestats.ui.MainActivity

@Suppress("DEPRECATION")
class HomeFragment : Fragment() {
    lateinit var userEntry : TextInputEditText

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // inflate the layout for this fragment
        val inflatedLayout = inflater.inflate(R.layout.fragment_home, container, false)
        // assign variables to buttons
        val searchButton = inflatedLayout.findViewById<Button>(R.id.searchButton)
        val xblButton = inflatedLayout.findViewById<Button>(R.id.xblButton)
        val psnButton = inflatedLayout.findViewById<Button>(R.id.psButton)
        val battleButton = inflatedLayout.findViewById<Button>(R.id.battleButton)
        // username = user input text
        userEntry = inflatedLayout.findViewById<TextInputEditText>(R.id.userEntry)
        var platform = ""

        // platform button listeners to change platform value and button appearance
        xblButton.setOnClickListener {
            "xbl".also { platform = it }
            xblButton.setBackgroundColor(resources.getColor(R.color.grey_0))
            battleButton.setBackgroundColor(resources.getColor(R.color.grey_2))
            psnButton.setBackgroundColor(resources.getColor(R.color.grey_2))
        }
        psnButton.setOnClickListener {
            "psn".also { platform = it }
            psnButton.setBackgroundColor(resources.getColor(R.color.grey_0))
            battleButton.setBackgroundColor(resources.getColor(R.color.grey_2))
            xblButton.setBackgroundColor(resources.getColor(R.color.grey_2))
        }
        battleButton.setOnClickListener {
            "battle".also { platform = it }
            battleButton.setBackgroundColor(resources.getColor(R.color.grey_0))
            xblButton.setBackgroundColor(resources.getColor(R.color.grey_2))
            psnButton.setBackgroundColor(resources.getColor(R.color.grey_2))
        }

        // search button on click listener
        searchButton.setOnClickListener {
            val username = userEntry.text.toString().replace("#", "%23")
            val playerModel = PlayerNetworkService.getPlayerData(username, platform)
            val matchModel = MatchNetworkService.getMatchData(username, platform)

            if (username == ""){
                var toast = Toast.makeText(context, "Error: Please enter a Username", Toast.LENGTH_SHORT)
                toast.setGravity(Gravity.TOP or Gravity.CENTER, 0, 0)
                toast.show()
            }
            if (platform == ""){
                var toast = Toast.makeText(context, "Error: Please select a Platform", Toast.LENGTH_SHORT)
                toast.setGravity(Gravity.TOP or Gravity.CENTER, 0, 0)
                toast.show()
            } else if (playerModel != null && matchModel != null){
                PlayerService.currentPlayerModel = playerModel
                MatchService.currentMatchModel = matchModel
                var mainActivity = this.requireActivity() as MainActivity
                mainActivity.navigateToPlayerProfile()
            } else if (playerModel == null && matchModel == null) {
                var toast = Toast.makeText(context, "Error: User not Found", Toast.LENGTH_SHORT)
                toast.setGravity(Gravity.TOP or Gravity.CENTER, 0, 0)
                toast.show()
            }
        }
        return inflatedLayout
    }
}

