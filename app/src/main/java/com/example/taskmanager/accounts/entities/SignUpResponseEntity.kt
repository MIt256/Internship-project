package com.example.taskmanager.accounts.entities

data class SignUpResponseEntity(
    val avatar_url: Any,
    val email: String,
    val id: String,
    val user_session: UserSession,
    val username: String
)