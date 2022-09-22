package com.example.taskmanager.accounts.entities

data class SignOutResponse(
    val `data`: SignOutData
)

data class SignOutData(
    val success: Boolean
)

