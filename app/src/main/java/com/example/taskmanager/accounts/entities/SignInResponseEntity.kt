package com.example.taskmanager.accounts.entities

data class SignInResponseEntity(
    val `data`: SignInData
)

data class SignInData(
    val access_token: String,
    val expires_in: Long,
    val refresh_token: String,
    val token_type: String
)