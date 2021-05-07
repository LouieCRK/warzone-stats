package com.louie.warzonestats.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.google.android.material.textfield.TextInputEditText
import com.louie.warzonestats.MainActivity
import com.louie.warzonestats.R
import com.louie.warzonestats.networking.services.PlayerNetworkService
import com.louie.warzonestats.services.PlayerService

class HomeFragment : Fragment() {
    lateinit var userEntry : TextInputEditText

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val inflatedLayout = inflater.inflate(R.layout.fragment_home, container, false)
        val searchButton = inflatedLayout.findViewById<Button>(R.id.searchButton)
        userEntry = inflatedLayout.findViewById<TextInputEditText>(R.id.userEntry)
        // todo: implement selection of button according to platform 'psn', 'battle', 'xbl'
        searchButton.setOnClickListener {
            val username = userEntry.text.toString().replace("#", "%23")
            val platform = "battle"
            val playerModel = PlayerNetworkService.getPlayerData(username, platform)
            if(playerModel == null){
                print("something went wrong")
            } else {
                PlayerService.currentPlayerModel = playerModel
                var mainActivity = this.requireActivity() as MainActivity
                mainActivity.navigateToPlayerProfile()
            }
        }
        return inflatedLayout
    }
}

