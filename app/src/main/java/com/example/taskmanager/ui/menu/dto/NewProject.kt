package com.example.taskmanager.ui.menu.dto

import com.google.gson.annotations.SerializedName

data class NewProjectRequest(
    @SerializedName("color")
    val color: String,
    @SerializedName("owner_id")
    val ownerId: String,
    @SerializedName("title")
    val title: String
)