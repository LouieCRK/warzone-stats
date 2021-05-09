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

        // todo - error messages with toast
//        var toast = Toast.makeText(context, "Test", Toast.LENGTH_SHORT)
//        toast.setGravity(Gravity.TOP or Gravity.CENTER, 0, 0)
//        toast.show()

        // platform button listeners to change platform value and button appearance
        xblButton.setOnClickListener {
            platform = "xbl"
            xblButton.setBackgroundColor(resources.getColor(R.color.grey_0))
            battleButton.setBackgroundColor(resources.getColor(R.color.grey_2))
            psnButton.setBackgroundColor(resources.getColor(R.color.grey_2))
        }
        psnButton.setOnClickListener {
            platform = "psn"
            psnButton.setBackgroundColor(resources.getColor(R.color.grey_0))
            battleButton.setBackgroundColor(resources.getColor(R.color.grey_2))
            xblButton.setBackgroundColor(resources.getColor(R.color.grey_2))
        }
        battleButton.setOnClickListener {
            platform = "battle"
            battleButton.setBackgroundColor(resources.getColor(R.color.grey_0))
            xblButton.setBackgroundColor(resources.getColor(R.color.grey_2))
            psnButton.setBackgroundColor(resources.getColor(R.color.grey_2))
        }

        searchButton.setOnClickListener {
            val username = userEntry.text.toString().replace("#", "%23")
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

