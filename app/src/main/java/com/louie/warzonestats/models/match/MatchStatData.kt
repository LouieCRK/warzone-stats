package com.louie.warzonestats.models.match

data class MatchStatData(
    val matchPlayerCount: Int,
    val mode: String,
    val player10: Double,
    val player25: Double,
    val playerAverage: Double,
    val playerBottom25: Double,
    val playerCount: Int,
    val playerMedian: Double,
    val team10: Double,
    val team25: Double,
    val teamAverage: Double,
    val teamBottom25: Double,
    val teamMedian: Double,
    val version: Int
)