package com.example.taskmanager.accounts.entities

import com.google.gson.annotations.SerializedName

data class SignInResponseEntity(
    val `data`: SignInData
)

data class SignInData(
    @SerializedName("access_token")
    val accessToken: String,
    @SerializedName("expires_in")
    val expiresIn: Long,
    @SerializedName("refresh_token")
    val refreshToken: String,
    @SerializedName("token_type")
    val tokenType: String
)