package com.example.taskmanager.data.remote.model.accounts

import com.google.gson.annotations.SerializedName

data class RefreshTokenResponse(
    val `data`: RefreshTokenData
)

data class RefreshTokenData(
    @SerializedName("access_token")
    val accessToken: String,
    @SerializedName("expires_in")
    val expiresIn: Long,
    @SerializedName("refresh_token")
    val refreshToken: String,
    @SerializedName("token_type")
    val tokenType: String
)