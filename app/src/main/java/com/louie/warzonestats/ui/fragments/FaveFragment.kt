package com.louie.warzonestats.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.louie.warzonestats.R

class FaveFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // inflate the layout for this fragment
        val inflatedLayout = inflater.inflate(R.layout.fragment_fave, container, false)

        // todo - when I can access playerButtons from FaveFragment - implement logic to search for profile onclick of fave button
        // assign variable to buttons
        val playerButton_0 = inflatedLayout.findViewById<Button>(R.id.playerButton_0)

        playerButton_0.setOnClickListener {
            playerButton_0.text = "USERNAME"
        }

        return inflatedLayout
    }

}