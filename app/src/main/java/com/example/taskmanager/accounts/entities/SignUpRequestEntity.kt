package com.example.taskmanager.accounts.entities

data class SignUpRequestEntity(
    val email: String,
    val password: String,
    val username: String
)