package com.louie.warzonestats.models.player

data class Last100games(
    val damageDone: Int,
    val damageTaken: Int,
    val deaths: Int,
    val gamesPlayed: Int,
    val gulagWinPercentage: Double,
    val headshots: Int,
    val kills: Int,
    val wins: Int
)