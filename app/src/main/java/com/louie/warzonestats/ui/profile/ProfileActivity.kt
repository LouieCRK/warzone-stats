package com.louie.warzonestats.ui.profile

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.louie.warzonestats.R
import com.louie.warzonestats.models.player.PlayerModel
import com.louie.warzonestats.services.PlayerService


class ProfileActivity : AppCompatActivity() {

    var playerModel : PlayerModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        // removes generic title bar
        this.supportActionBar?.hide()
        // assign variable to currentPlayerModel
        playerModel = PlayerService.currentPlayerModel

        // assign variables to required model directories
        var lifetimeStatsBR = playerModel!!.data.lifetime.mode.br.properties
        var user_battle = playerModel!!.battleNetId
        var user_xbl = playerModel!!.xblId
        var user_psn = playerModel!!.psnId
        var username = ""
        var kd_league = ""

        // assign variables to data values needed to present lifetime player stats
        var lifetime_KD = String.format("%.2f", lifetimeStatsBR.kdRatio).toDouble()
        var lifetime_Wins = lifetimeStatsBR.wins
        var lifetime_Kills = lifetimeStatsBR.kills
        var lifetime_WinRate = (lifetimeStatsBR.gamesPlayed / lifetimeStatsBR.wins).toString()
        var lifetime_KillsPerGame = String.format(
            "%.2f",
            lifetimeStatsBR.kills.toDouble() / lifetimeStatsBR.gamesPlayed.toDouble()
        )

        // logic to set username to correct value and not null
        if (user_battle == null && user_psn == null) {
            username = user_xbl as String
        } else if (user_battle == null && user_xbl == null) {
            username = user_psn as String
        } else if (user_psn == null && user_xbl == null) {
            username = user_battle as String
        }

        // logic to set lifetime KD to corresponding league
        if (lifetime_KD >= 3.57) {
            kd_league = "legend"
        } else if (lifetime_KD < 3.57 && lifetime_KD > 2.08) {
            kd_league = "master"
        } else if (lifetime_KD < 2.08 && lifetime_KD > 1.14) {
            kd_league = "diamond"
        }

        // set player username to textView within Profile Activity
        var viewUsername = findViewById<View>(R.id.username_text) as TextView
        viewUsername.text = username.toUpperCase()

        // LIFETIME STATS
            // KD
                // set player lifetime KD to textView within ProfileActivity
                var viewLifetimeKD = findViewById<View>(R.id.lifetime_kd_text) as TextView
                viewLifetimeKD.text = lifetime_KD.toString()

                // set player lifetime KD to textView within ProfileActivity
                var viewLeagueKD = findViewById<View>(R.id.league_kd_text) as TextView
                viewLeagueKD.text = kd_league.capitalize()

                // set background plate as drawable league gradient
                var viewLeagueKD_box = findViewById<View>(R.id.box_kd) as TextView
                viewLeagueKD_box.background = ContextCompat.getDrawable(this@ProfileActivity, R.drawable.box_diamond)


            // WINS

            // KILLS

            // WIN %

            // KILLS PER GAME

        // WEEKLY STATS
            // KD

            // KILLS PER GAME

            // KILLS



    }
}

//crook#21832