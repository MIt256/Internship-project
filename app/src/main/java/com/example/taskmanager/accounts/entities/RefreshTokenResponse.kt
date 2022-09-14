package com.example.taskmanager.accounts.entities

data class RefreshTokenResponse(
    val `data`: RefreshTokenData
)

data class RefreshTokenData(
    val access_token: String,
    val expires_in: Long,
    val refresh_token: String,
    val token_type: String
)