package com.example.taskmanager.data.remote.model.accounts

data class SignUpRequestEntity(
    val email: String,
    val password: String,
    val username: String
)