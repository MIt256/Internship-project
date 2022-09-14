package com.example.taskmanager.accounts.entities

data class SignUpResponseEntity(
    val `data`: SignUpData
)

data class SignUpData(
    val avatar_url: Any,
    val email: String,
    val id: String,
    val user_session: UserSession,
    val username: String
)

data class UserSession(
    val access_token: String,
    val expires_in: Long,
    val refresh_token: String,
    val token_type: String
)