package com.louie.warzonestats.models.match

data class Player(
    val damageDone: Int,
    val damageTaken: Int,
    val deaths: Int,
    val gulagWin: Boolean,
    val headshots: Int,
    val killPlacement: Int,
    val kills: Int,
    val uno: String,
    val username: String
)