package com.example.taskmanager.data.remote.model.accounts

import com.example.taskmanager.ui.entities.UserSettings
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
    val tokenType: String,
    @SerializedName("user_id")
    val userId: String
) {
    fun toUserSettings(email: String) = UserSettings(
        avatarUrl = null,
        email = email,
        id = userId,
        username = null,
        accessToken = accessToken,
        expiresIn = expiresIn,
        refreshToken = refreshToken,
        tokenType = tokenType
    )
}