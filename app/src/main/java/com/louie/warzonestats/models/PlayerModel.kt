package com.louie.warzonestats.models

data class PlayerModel(
    val activisionId: Any,
    val battleNetId: String,
    val createdAt: String,
    val `data`: Data,
    val last100games: Last100games,
    val psnId: Any,
    val records: List<Record>,
    val refreshAt: Int,
    val uno: String,
    val updatedAt: String,
    val user: User,
    val xblId: Any
)