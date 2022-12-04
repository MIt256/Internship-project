package com.example.taskmanager.data.remote.model.qiuck

import com.google.gson.annotations.SerializedName

data class NewQuickRequest(
    @SerializedName("description")
    val description: String,
    @SerializedName("color")
    val color: String,
    @SerializedName("owner_id")
    val ownerId: String,
)
