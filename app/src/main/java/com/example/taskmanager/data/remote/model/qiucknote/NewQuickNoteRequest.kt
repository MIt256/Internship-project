package com.example.taskmanager.data.remote.model.qiucknote

import com.google.gson.annotations.SerializedName

data class NewQuickNoteRequest(
    @SerializedName("description")
    val description: String,
    @SerializedName("color")
    val color: String,
    @SerializedName("owner_id")
    val ownerId: String,
)
