package com.example.taskmanager.accounts.dto

data class SignUpRequestEntity(
    val email: String,
    val password: String,
    val username: String
)