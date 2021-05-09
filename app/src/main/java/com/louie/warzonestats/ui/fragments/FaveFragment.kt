package com.louie.warzonestats.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.louie.warzonestats.R

class FaveFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // inflate the layout for this fragment
        val inflatedLayout = inflater.inflate(R.layout.fragment_fave, container, false)


        return inflatedLayout
    }

}