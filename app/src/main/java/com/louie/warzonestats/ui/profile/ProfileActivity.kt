package com.louie.warzonestats.ui.profile

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
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

        playerModel = PlayerService.currentPlayerModel

        var lifetimeStatsBR = playerModel!!.data.lifetime.mode.br.properties

        var lifetime_KD = lifetimeStatsBR.kdRatio
        var lifetime_Wins = lifetimeStatsBR.wins
        var lifetime_Kills = lifetimeStatsBR.kills
        var lifetime_WinRate = (lifetimeStatsBR.gamesPlayed / lifetimeStatsBR.wins)
        var lifetime_KillsPerGame = (lifetimeStatsBR.kills / lifetimeStatsBR.gamesPlayed)

        println("$lifetime_KD, $lifetime_Wins, $lifetime_Kills, $lifetime_WinRate, $lifetime_KillsPerGame}")

        println("K/D Ratio: ${lifetimeStatsBR.kdRatio}")

    }
}

//crook#21832