package com.example.taskmanager.data.remote.model.accounts

data class SignOutResponse(
    val `data`: SignOutData
)

data class SignOutData(
    val success: Boolean
)

