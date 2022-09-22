package com.example.taskmanager.accounts.entities

import com.google.gson.annotations.SerializedName

data class SignUpResponseEntity(
    @SerializedName("data")
    val `data`: SignUpData
)

data class SignUpData(
    @SerializedName("avatar_url")
    val avatarUrl: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("id")
    val id: String,
    @SerializedName("user_session")
    val userSession: UserSession,
    @SerializedName("username")
    val username: String
)

data class UserSession(
    @SerializedName("access_token")
    val accessToken: String,
    @SerializedName("expires_in")
    val expiresIn: Long,
    @SerializedName("refresh_token")
    val refreshToken: String,
    @SerializedName("token_type")
    val tokenType: String
)