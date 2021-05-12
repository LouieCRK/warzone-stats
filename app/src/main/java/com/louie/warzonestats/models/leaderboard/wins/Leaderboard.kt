package com.louie.warzonestats.models.leaderboard.wins

data class Leaderboard(
    val `data`: List<Data>,
    val id: String,
    val leaderboardCategory: Int,
    val leaderboardRangeType: Int,
    val leaderboardType: Int,
    val startedAt: String
)