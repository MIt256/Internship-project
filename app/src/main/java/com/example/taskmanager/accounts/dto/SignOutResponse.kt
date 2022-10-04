package com.example.taskmanager.accounts.dto

data class SignOutResponse(
    val `data`: SignOutData
)

data class SignOutData(
    val success: Boolean
)

