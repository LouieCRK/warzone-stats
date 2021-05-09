package com.louie.warzonestats.ui.profile

import android.icu.text.NumberFormat
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.louie.warzonestats.R
import com.louie.warzonestats.models.match.MatchModel
import com.louie.warzonestats.models.player.PlayerModel
import com.louie.warzonestats.services.MatchService
import com.louie.warzonestats.services.PlayerService
import java.util.*


class ProfileActivity : AppCompatActivity() {

    var playerModel : PlayerModel? = null
    var matchModel : MatchModel? = null

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        // removes generic title bar
        this.supportActionBar?.hide()
        // assign variable to models
        playerModel = PlayerService.currentPlayerModel
        matchModel = MatchService.currentMatchModel

        // assign variables to required model directories
        var lifetimeStatsBR = playerModel!!.data.lifetime.mode.br.properties
        var weeklyStatsBR = playerModel!!.data.weekly.mode.br_all.properties
        var playerMatches = matchModel!!
        var username = ""
        var kd_league = ""

        var matchIndex = 0
        for (i in playerMatches){
            matchIndex = matchIndex + 1
            println(i)

            // breaks loop when the 10th match is loaded (we only want 10 most recent matches
            if (matchIndex > 9){
                break
            }
        }
//        println(playerMatches[])

        // format stats ready for view
        // lifetime player stats - assign data to variables
        var lifetime_KD = String.format("%.2f", lifetimeStatsBR.kdRatio).toDouble()
        var lifetime_Wins = lifetimeStatsBR.wins
        var lifetime_Kills = NumberFormat.getNumberInstance(Locale.US).format(lifetimeStatsBR.kills)
        var lifetime_WinRate = String.format("%.2f", lifetimeStatsBR.wins.toDouble() / lifetimeStatsBR.gamesPlayed.toDouble() * 100)
        var lifetime_KillsPerGame = String.format("%.2f", lifetimeStatsBR.kills.toDouble() / lifetimeStatsBR.gamesPlayed.toDouble())

        // weekly player stats - assign data to variables
        var weekly_KD = String.format("%.2f", weeklyStatsBR.kdRatio).toDouble()
        var weekly_KillsPerGame = String.format("%.2f", weeklyStatsBR.killsPerGame).toDouble()
        var weekly_Kills = weeklyStatsBR.kills
        var weekly_Matches = weeklyStatsBR.matchesPlayed


        // call League_KD() function to get corresponding league name & drawable
        kd_league = League_KD(lifetime_KD, kd_league)

        // set player username to textView within Profile Activity
        var viewUsername = findViewById<View>(R.id.username_text) as TextView
        viewUsername.text = username.toUpperCase()

        // LIFETIME STATS
            // KD
                // set player lifetime KD to textView
                var viewLifetimeKD = findViewById<View>(R.id.lifetime_kd_text) as TextView
                viewLifetimeKD.text = lifetime_KD.toString()
                // set player lifetime KD to textView
                var viewLeagueKD = findViewById<View>(R.id.league_kd_text) as TextView
                viewLeagueKD.text = kd_league.capitalize()
            // WINS
                // set player lifetime wins to textView
                var viewLifetimeWins = findViewById<View>(R.id.lifetime_wins_text) as TextView
                viewLifetimeWins.text = lifetime_Wins.toString()
            // KILLS
                // set player lifetime kills to textView
                var viewLifetimeKills = findViewById<View>(R.id.lifetime_kills_text) as TextView
                viewLifetimeKills.text = lifetime_Kills.toString()
            // WIN %
                // set player lifetime win percentage to textView
                var viewLifetimeWinrate = findViewById<View>(R.id.lifetime_winrate_text) as TextView
                viewLifetimeWinrate.text = lifetime_WinRate.toString()
            // KILLS PER GAME
                // set player lifetime win percentage to textView
                var viewLifetimeKPG = findViewById<View>(R.id.lifetime_killsPerGame_text) as TextView
                viewLifetimeKPG.text = lifetime_KillsPerGame.toString()

        // WEEKLY STATS
        // todo - add logic that compares weekly stats to lifetime
        // todo - red box colour = worse performance, green box colour = better performance
            // MATCHES PLAYED
                // set player weekly matches played to textView
                var viewWeeklyMatches = findViewById<View>(R.id.weeklyMatches_text) as TextView
                viewWeeklyMatches.text = ("$weekly_Matches MATCHES PLAYED")
            // KD
                // set player weekly matches played to textView
                var viewWeeklyKD = findViewById<View>(R.id.weeklyKD_text) as TextView
                viewWeeklyKD.text = weekly_KD.toString()
            // KILLS PER GAME
                // set player weekly matches played to textView
                var viewWeeklyKPG = findViewById<View>(R.id.weeklyKPG_text) as TextView
                viewWeeklyKPG.text = weekly_KillsPerGame.toString()
            // KILLS
                // set player weekly matches played to textView
                var viewWeeklyKills = findViewById<View>(R.id.weeklyKills_text) as TextView
                viewWeeklyKills.text = weekly_Kills.toString()


//            // todo - add to favourites button onclick
//            var faveButton = findViewById<Button>(R.id.faveButton)
//            // todo - work out how to reference fragment button from activity
//            var playerButton_0 = findViewById<Button>(R.id.playerButton_0) as Button
//
//            faveButton.setOnClickListener {
//            playerButton_0.text = username
//            }


    }

    // todo - continue setting logic for all lifetime stats
    // logic to set lifetime KD and containers to corresponding leagues
    fun League_KD(lifetime_KD: Double, kd_league: String): String {
        var viewLeagueKD_box = findViewById<View>(R.id.box_kd) as TextView
        var kd_league1 = kd_league

        if (lifetime_KD >= 3.57) {
            kd_league1 = "legend"
            viewLeagueKD_box.background =
                ContextCompat.getDrawable(this@ProfileActivity, R.drawable.box_legend)
        } else if (lifetime_KD < 3.57 && lifetime_KD > 2.08) {
            kd_league1 = "master"
            viewLeagueKD_box.background =
                ContextCompat.getDrawable(this@ProfileActivity, R.drawable.box_master)
        } else if (lifetime_KD < 2.08 && lifetime_KD > 1.14) {
            kd_league1 = "diamond"
            viewLeagueKD_box.background =
                ContextCompat.getDrawable(this@ProfileActivity, R.drawable.box_diamond)
        }
        return kd_league1
    }

}
