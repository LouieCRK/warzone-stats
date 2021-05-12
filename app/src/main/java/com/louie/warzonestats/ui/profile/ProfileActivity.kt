package com.louie.warzonestats.ui.profile

import android.annotation.SuppressLint
import android.icu.text.NumberFormat
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.louie.warzonestats.R
import com.louie.warzonestats.R.drawable.*
import com.louie.warzonestats.models.match.MatchModel
import com.louie.warzonestats.models.player.PlayerModel
import com.louie.warzonestats.services.MatchService
import com.louie.warzonestats.services.PlayerService
import java.util.*

@Suppress("SameParameterValue", "SENSELESS_COMPARISON", "VARIABLE_WITH_REDUNDANT_INITIALIZER")
class ProfileActivity : AppCompatActivity() {

    private var playerModel : PlayerModel? = null
    private var matchModel : MatchModel? = null

    @SuppressLint("SetTextI18n")
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

        // format stats ready for view
        // lifetime player stats - assign data to variables
        val lifetimeKD = String.format("%.2f", lifetimeStatsBR.kdRatio).toDouble()
        val lifetimeWins = lifetimeStatsBR.wins
        val lifetimeWinRate = String.format("%.2f", lifetimeStatsBR.wins.toDouble() / lifetimeStatsBR.gamesPlayed.toDouble() * 100)
        val lifetimeKPG = String.format("%.2f", lifetimeStatsBR.kills.toDouble() / lifetimeStatsBR.gamesPlayed.toDouble())

        // weekly player stats - assign data to variables
        val weeklyKD = String.format("%.2f", weeklyStatsBR.kdRatio).toDouble()
        val weeklyKPG = String.format("%.2f", weeklyStatsBR.killsPerGame).toDouble()
        val weeklyKills = weeklyStatsBR.kills
        val weeklyMatches = weeklyStatsBR.matchesPlayed

        // set player username to textView within Profile Activity
        val viewUsername = findViewById<View>(R.id.username_text) as TextView
        viewUsername.text = playerModel!!.data.uno.toUpperCase(Locale.ROOT)

        // call LeagueKD() function to get corresponding league position & drawable
        val leagueKD = leagueKD(lifetimeKD, "")
        // call LeagueWins() function to get corresponding league position & drawable
        val leagueWins = leagueWins(lifetimeWins, "")
        // call leagueKills() function to get corresponding league position & drawable
        val leagueKills = leagueKills(lifetimeStatsBR.kills, "")

        // LIFETIME STATS
            // KD
                // set player lifetime KD to textView
                val viewLifetimeKD = findViewById<View>(R.id.lifetime_kd_text) as TextView
                viewLifetimeKD.text = lifetimeKD.toString()
                // set player lifetime KD league to textView
                val viewLeagueKD = findViewById<View>(R.id.league_kd_text) as TextView
                viewLeagueKD.text = leagueKD.capitalize(Locale.ROOT)
            // WINS
                // set player lifetime wins to textView
                val viewLifetimeWins = findViewById<View>(R.id.lifetime_wins_text) as TextView
                viewLifetimeWins.text = lifetimeWins.toString()
                // set player lifetime KD league to textView
                val viewLeagueWins = findViewById<View>(R.id.league_wins_text) as TextView
                viewLeagueWins.text = leagueWins.capitalize(Locale.ROOT)
            // KILLS
                // set player lifetime kills to textView
                val viewLifetimeKills = findViewById<View>(R.id.lifetime_kills_text) as TextView
                val lifetimeKills = NumberFormat.getNumberInstance(Locale.US).format(lifetimeStatsBR.kills)
                viewLifetimeKills.text = lifetimeKills.toString()
                // set player lifetime kills league to textView
                val viewLeagueKills = findViewById<View>(R.id.league_kills_text) as TextView
                viewLeagueKills.text = leagueKills.capitalize(Locale.ROOT)
            // WIN %
                // set player lifetime win percentage to textView
                val viewLifetimeWinRate = findViewById<View>(R.id.lifetime_winrate_text) as TextView
                viewLifetimeWinRate.text = lifetimeWinRate
            // KILLS PER GAME
                // set player lifetime win percentage to textView
                val viewLifetimeKPG = findViewById<View>(R.id.lifetime_killsPerGame_text) as TextView
                viewLifetimeKPG.text = lifetimeKPG

        // WEEKLY STATS
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

        // LAST 10 GAMES
            // league distribution variables, used as counter to assign league distribution height
            var leagueEmerald = 0;var leagueDiamond = 0; var leagueGold = 0
            var leagueSilver = 0; var leagueBronze = 0
            // create a matchIndex variable
            var matchIndex = 0
            // iterate over matches within playerMatches
            for (match in playerMatches){
                var plunderMatchCheck = false
                var matchAvgKD = 0.0
                var plunderMatch = ""
                matchIndex ++

                // assign text view identifier + our match index to variable
                // kills
                val kills = resources.getIdentifier("killsMatch_$matchIndex", "id", packageName)
                val viewMatchKills = findViewById<View>(kills) as TextView
                // position
                val position = resources.getIdentifier("positionMatch_$matchIndex", "id", packageName)
                val viewMatchPosition = findViewById<View>(position) as TextView
                // game mode
                val gameMode = resources.getIdentifier("modeMatch_$matchIndex", "id", packageName)
                val viewGameMode = findViewById<View>(gameMode) as TextView
                // league
                val lobbyKD = resources.getIdentifier("leagueMatch_$matchIndex", "id", packageName)
                val viewMatchKD = findViewById<View>(lobbyKD) as TextView

                // issue with API - some match stat data comes back as null, to avoid error we will just assign an unknown league
                matchAvgKD = if (match.matchStatData == null) 0.0 else {
                    String.format("%.2f", match.matchStatData.playerAverage + match.matchStatData.playerMedian / 2).toDouble()
                }

                // if mode is plunder, replace string to be readable
                if (match.mode == "plunder_trios" || match.mode == "plunder_quads" ){
                    plunderMatch = match.mode.replace("_", " ").toUpperCase(Locale.ROOT)
                    plunderMatchCheck = true
                }

                // break down string into readable format, assign position view text
                if (plunderMatchCheck){
                    viewGameMode.text = plunderMatch
                    // error with api, when player queues for a 'plunder match' the position is auto set to 0
                    viewMatchPosition.text = "N/A"
                }else{
                    viewGameMode.text = match.mode.replace("br_br", "br ").toUpperCase(Locale.ROOT)
                    viewMatchPosition.text = match.position.toString()
                }

                // if player position is 1st place, use green box
                if (viewMatchPosition.text == "1"){
                    viewMatchPosition.background = ContextCompat.getDrawable(this@ProfileActivity, box_winner)
                }

                // assign league name and league color from match average kd
                if (matchAvgKD > 2){
                    viewMatchKD.text = "Emerald"
                    viewMatchKD.background = ContextCompat.getDrawable(this@ProfileActivity, box_emerald)
                    leagueEmerald += 1
                };if (matchAvgKD > 1.7 && matchAvgKD < 2){
                    viewMatchKD.text = "Diamond"
                    viewMatchKD.background = ContextCompat.getDrawable(this@ProfileActivity, box_diamond)
                    leagueDiamond += 1
                };if (matchAvgKD > 1.5 && matchAvgKD < 1.7){
                    viewMatchKD.text = "Gold"
                    viewMatchKD.background = ContextCompat.getDrawable(this@ProfileActivity, box_gold)
                    leagueGold += 1
                };if (matchAvgKD > 1 && matchAvgKD < 1.5){
                    viewMatchKD.text = "Silver"
                    viewMatchKD.background = ContextCompat.getDrawable(this@ProfileActivity, box_silver)
                    leagueSilver += 1
                };if (matchAvgKD > 0 && matchAvgKD < 1){
                    viewMatchKD.text = "Bronze"
                    viewMatchKD.background = ContextCompat.getDrawable(this@ProfileActivity, box_bronze)
                    leagueBronze += 1
                };if (matchAvgKD == 0.0){
                    viewMatchKD.text = "Unknown"
                    viewMatchKD.background = ContextCompat.getDrawable(this@ProfileActivity, box_unknown)
                }

                // assign stat to views text
                viewMatchKills.text = match.kills.toString()
                viewMatchPosition.text = match.position.toString()

                // breaks loop when the 10th match is loaded (10 most recent matches)
                if (matchIndex >= 10){
                    break
                }
            }

        // assign variable to text view of league distribution bars
        val emeraldBar = findViewById<View>(R.id.gamesBox_emerald) as TextView
        val diamondBar = findViewById<View>(R.id.gamesBox_diamond) as TextView
        val goldBar = findViewById<View>(R.id.gamesBox_gold) as TextView
        val silverBar = findViewById<View>(R.id.gamesBox_silver) as TextView
        val bronzeBar = findViewById<View>(R.id.gamesBox_bronze) as TextView

        // assign layout heights to distribution bars depending on their value
        emeraldBar.layoutParams.height  = ((leagueEmerald * 52.5).toInt())
        diamondBar.layoutParams.height = ((leagueDiamond * 52.5).toInt())
        goldBar.layoutParams.height = ((leagueGold * 52.5).toInt())
        silverBar.layoutParams.height = ((leagueSilver * 52.5).toInt())
        bronzeBar.layoutParams.height = ((leagueBronze * 52.5).toInt())

        // todo - work out how to reference player buttons from FaveFragment
//        var faveButton: Button? = findViewById(R.id.faveButton)
//        var playerButton_0 = findViewById<Button>(R.id.playerButton_0)
//
//        faveButton?.setOnClickListener {
//            playerButton_0.visibility = View.VISIBLE
//            playerButton_0.text = playerModel!!.data.uno.toUpperCase(Locale.ROOT)
//        }

    }

    // logic to set lifetime KD and containers to corresponding leagues
    private fun leagueKD(lifetimeKD: Double, emptyLeague: String): String {
        val viewLeagueBoxKD = findViewById<View>(R.id.box_kd) as TextView
        var leagueKD = emptyLeague

        when {
            lifetimeKD >= 3.57 -> {
                leagueKD = "legend"
                viewLeagueBoxKD.background = ContextCompat.getDrawable(this@ProfileActivity, box_legend)
            }
            lifetimeKD < 3.57 && lifetimeKD > 2.08 -> {
                leagueKD = "master"
                viewLeagueBoxKD.background = ContextCompat.getDrawable(this@ProfileActivity, box_master)
            }
            lifetimeKD < 2.08 && lifetimeKD > 1.14 -> {
                leagueKD = "emerald"
                viewLeagueBoxKD.background = ContextCompat.getDrawable(this@ProfileActivity, box_emerald)
            }
            lifetimeKD < 1.14 && lifetimeKD > 0.92 -> {
                leagueKD = "diamond"
                viewLeagueBoxKD.background = ContextCompat.getDrawable(this@ProfileActivity, box_diamond)
            }
            lifetimeKD < 1.14 && lifetimeKD > 0.92 -> {
                leagueKD = "gold"
                viewLeagueBoxKD.background = ContextCompat.getDrawable(this@ProfileActivity, box_gold)
            }
            lifetimeKD < 0.92 && lifetimeKD > 0.74 -> {
                leagueKD = "silver"
                viewLeagueBoxKD.background = ContextCompat.getDrawable(this@ProfileActivity, box_silver)
            }
            lifetimeKD < 0.74 -> {
                leagueKD = "bronze"
                viewLeagueBoxKD.background = ContextCompat.getDrawable(this@ProfileActivity, box_bronze)
            }
        }
        return leagueKD
    }

    // logic to set lifetime wins and containers to corresponding leagues
    private fun leagueWins(lifetimeWins: Int, emptyLeague: String): String {
        val viewLeagueBoxWins = findViewById<View>(R.id.box_wins) as TextView
        var leagueWins = emptyLeague

        when {
            lifetimeWins >= 232 -> {
                leagueWins = "legend"
                viewLeagueBoxWins.background = ContextCompat.getDrawable(this@ProfileActivity, box_legend)
            }
            lifetimeWins in 140..231 -> {
                leagueWins = "master"
                viewLeagueBoxWins.background = ContextCompat.getDrawable(this@ProfileActivity, box_master)
            }
            lifetimeWins in 70..139 -> {
                leagueWins = "emerald"
                viewLeagueBoxWins.background = ContextCompat.getDrawable(this@ProfileActivity, box_emerald)
            }
            lifetimeWins in 31..69 -> {
                leagueWins = "diamond"
                viewLeagueBoxWins.background = ContextCompat.getDrawable(this@ProfileActivity, box_diamond)
            }
            lifetimeWins in 21..30 -> {
                leagueWins = "gold"
                viewLeagueBoxWins.background = ContextCompat.getDrawable(this@ProfileActivity, box_gold)
            }
            lifetimeWins in 11..19 -> {
                leagueWins = "silver"
                viewLeagueBoxWins.background = ContextCompat.getDrawable(this@ProfileActivity, box_silver)
            }
            lifetimeWins < 10 -> {
                leagueWins = "bronze"
                viewLeagueBoxWins.background = ContextCompat.getDrawable(this@ProfileActivity, box_bronze)
            }
        }
        return leagueWins
    }

    // logic to set lifetime kills and containers to corresponding leagues
    private fun leagueKills(lifetimeKills: Int, emptyLeague: String): String {
        val viewLeagueBoxKills = findViewById<View>(R.id.box_kills) as TextView
        var leagueKills = emptyLeague

        when {
            lifetimeKills >= 12875 -> {
                leagueKills = "legend"
                viewLeagueBoxKills.background = ContextCompat.getDrawable(this@ProfileActivity, box_legend)
            }
            lifetimeKills in 7616..12874 -> {
                leagueKills = "master"
                viewLeagueBoxKills.background = ContextCompat.getDrawable(this@ProfileActivity, box_master)
            }
            lifetimeKills in 2075..7615 -> {
                leagueKills = "emerald"
                viewLeagueBoxKills.background = ContextCompat.getDrawable(this@ProfileActivity, box_emerald)
            }
            lifetimeKills in 1024..2074 -> {
                leagueKills = "diamond"
                viewLeagueBoxKills.background = ContextCompat.getDrawable(this@ProfileActivity, box_diamond)
            }
            lifetimeKills in 450..1023 -> {
                leagueKills = "gold"
                viewLeagueBoxKills.background = ContextCompat.getDrawable(this@ProfileActivity, box_gold)
            }
            lifetimeKills in 120..449 -> {
                leagueKills = "silver"
                viewLeagueBoxKills.background = ContextCompat.getDrawable(this@ProfileActivity, box_silver)
            }
            lifetimeKills < 120 -> {
                leagueKills = "bronze"
                viewLeagueBoxKills.background = ContextCompat.getDrawable(this@ProfileActivity, box_bronze)
            }
        }
        return leagueKills
    }
    // todo - win % league
    // todo - kills / game league

    // todo - add logic that compares weekly stats to lifetime (red box colour = worse performance, green box colour = better performance)
}
