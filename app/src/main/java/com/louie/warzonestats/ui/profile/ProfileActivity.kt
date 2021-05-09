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

@Suppress("SameParameterValue")
class ProfileActivity : AppCompatActivity() {

    private var playerModel : PlayerModel? = null
    private var matchModel : MatchModel? = null

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
        val lifetimeStatsBR = playerModel!!.data.lifetime.mode.br.properties
        val weeklyStatsBR = playerModel!!.data.weekly.mode.br_all.properties
        val playerMatches = matchModel!!

        // create a matchIndex variable
        var matchIndex = 0
        // iterate over matches within playerMatches
        for (match in playerMatches){
            matchIndex ++

            // error with api, when player queues for a 'plunder match' the position is set to 0
            if (match.mode == "plunder_trios" || match.mode == "plunder_quads" ){
                match.position = 1
            }

            // assign text view identifier + our match index to variable
            val kills = resources.getIdentifier("killsMatch_$matchIndex", "id", packageName)
            val viewMatchKills = findViewById<View>(kills) as TextView
            val position = resources.getIdentifier("positionMatch_$matchIndex", "id", packageName)
            val viewMatchPosition = findViewById<View>(position) as TextView

            // assign stat to views text
            viewMatchKills.text = match.kills.toString()
            viewMatchPosition.text = match.position.toString()

            // breaks loop when the 10th match is loaded (10 most recent matches)
            if (matchIndex >= 10){
                break
            }
        }

        // format stats ready for view
        // lifetime player stats - assign data to variables
        val lifetimeKD = String.format("%.2f", lifetimeStatsBR.kdRatio).toDouble()
        val lifetimeWins = lifetimeStatsBR.wins
        val lifetimeKills = NumberFormat.getNumberInstance(Locale.US).format(lifetimeStatsBR.kills)
        val lifetimeWinRate = String.format("%.2f", lifetimeStatsBR.wins.toDouble() / lifetimeStatsBR.gamesPlayed.toDouble() * 100)
        val lifetimeKPG = String.format("%.2f", lifetimeStatsBR.kills.toDouble() / lifetimeStatsBR.gamesPlayed.toDouble())

        // weekly player stats - assign data to variables
        val weeklyKD = String.format("%.2f", weeklyStatsBR.kdRatio).toDouble()
        val weeklyKPG = String.format("%.2f", weeklyStatsBR.killsPerGame).toDouble()
        val weeklyKills = weeklyStatsBR.kills
        val weeklyMatches = weeklyStatsBR.matchesPlayed

        // call League_KD() function to get corresponding league name & drawable
        val leagueKD = leagueKD(lifetimeKD, "")

        // set player username to textView within Profile Activity
        val viewUsername = findViewById<View>(R.id.username_text) as TextView
        viewUsername.text = playerModel!!.data.uno.toUpperCase(Locale.ROOT)

        // LIFETIME STATS
            // KD
                // set player lifetime KD to textView
                val viewLifetimeKD = findViewById<View>(R.id.lifetime_kd_text) as TextView
                viewLifetimeKD.text = lifetimeKD.toString()
                // set player lifetime KD to textView
                val viewLeagueKD = findViewById<View>(R.id.league_kd_text) as TextView
                viewLeagueKD.text = leagueKD.capitalize(Locale.ROOT)
        // WINS
                // set player lifetime wins to textView
                val viewLifetimeWins = findViewById<View>(R.id.lifetime_wins_text) as TextView
                viewLifetimeWins.text = lifetimeWins.toString()
            // KILLS
                // set player lifetime kills to textView
                val viewLifetimeKills = findViewById<View>(R.id.lifetime_kills_text) as TextView
                viewLifetimeKills.text = lifetimeKills.toString()
            // WIN %
                // set player lifetime win percentage to textView
                val viewLifetimeWinRate = findViewById<View>(R.id.lifetime_winrate_text) as TextView
                viewLifetimeWinRate.text = lifetimeWinRate
            // KILLS PER GAME
                // set player lifetime win percentage to textView
                val viewLifetimeKPG = findViewById<View>(R.id.lifetime_killsPerGame_text) as TextView
                viewLifetimeKPG.text = lifetimeKPG

        // WEEKLY STATS
        // todo - add logic that compares weekly stats to lifetime (red box colour = worse performance, green box colour = better performance)
            // MATCHES PLAYED
                // set player weekly matches played to textView
                val viewWeeklyMatches = findViewById<View>(R.id.weeklyMatches_text) as TextView
                viewWeeklyMatches.text = ("$weeklyMatches MATCHES PLAYED")
            // KD
                // set player weekly matches played to textView
                val viewWeeklyKD = findViewById<View>(R.id.weeklyKD_text) as TextView
                viewWeeklyKD.text = weeklyKD.toString()
            // KILLS PER GAME
                // set player weekly matches played to textView
                val viewWeeklyKPG = findViewById<View>(R.id.weeklyKPG_text) as TextView
                viewWeeklyKPG.text = weeklyKPG.toString()
            // KILLS
                // set player weekly matches played to textView
                val viewWeeklyKills = findViewById<View>(R.id.weeklyKills_text) as TextView
                viewWeeklyKills.text = weeklyKills.toString()

//            var faveButton = findViewById<Button>(R.id.faveButton)
//            // todo - work out how to reference FaveFragment button from ProfileActivity
//            var playerButton_0 = findViewById<Button>(R.id.playerButton_0)
//            // todo - add to favourites button onclick
//            faveButton.setOnClickListener {
//                playerButton_0.visibility = View.VISIBLE
//                playerButton_0.text = playerMatches[1].username.toUpperCase()
//            }
    }

    // todo - continue setting logic for all lifetime stats
    // logic to set lifetime KD and containers to corresponding leagues
    private fun leagueKD(lifetime_KD: Double, kd_league: String): String {
        val viewLeagueBoxKD = findViewById<View>(R.id.box_kd) as TextView
        var leagueKD = kd_league

        if (lifetime_KD >= 3.57) {
            leagueKD = "legend"
            viewLeagueBoxKD.background =
                ContextCompat.getDrawable(this@ProfileActivity, R.drawable.box_legend)
        } else if (lifetime_KD < 3.57 && lifetime_KD > 2.08) {
            leagueKD = "master"
            viewLeagueBoxKD.background =
                ContextCompat.getDrawable(this@ProfileActivity, R.drawable.box_master)
        } else if (lifetime_KD < 2.08 && lifetime_KD > 1.14) {
            leagueKD = "diamond"
            viewLeagueBoxKD.background =
                ContextCompat.getDrawable(this@ProfileActivity, R.drawable.box_diamond)
        }
        return leagueKD
    }

}
