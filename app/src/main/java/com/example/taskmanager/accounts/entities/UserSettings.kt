package com.example.taskmanager.accounts.entities

data class UserSettings(
    val avatarUrl: String?,
    val email: String,
    val id: String?,
    val username: String?,
    val accessToken: String?,
    val expiresIn: Long?,
    val refreshToken: String?,
    val tokenType: String?
)
