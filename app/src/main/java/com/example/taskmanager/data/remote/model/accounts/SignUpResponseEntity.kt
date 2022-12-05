package com.example.taskmanager.data.remote.model.accounts

import com.example.taskmanager.ui.entities.UserSettings
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
) {
    /**
     * Convert this entity into in-app [UserSession] instance. For AccountManager.
     */
    fun toUserSettings() = UserSettings(
        avatarUrl = avatarUrl,
        email = email,
        id = id,
        username = username,
        accessToken = userSession.accessToken,
        expiresIn = userSession.expiresIn,
        refreshToken = userSession.refreshToken,
        tokenType = userSession.tokenType
    )
}

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