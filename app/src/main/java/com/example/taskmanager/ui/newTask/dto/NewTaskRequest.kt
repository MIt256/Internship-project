package com.example.taskmanager.ui.newTask.dto

import com.google.gson.annotations.SerializedName

data class NewTaskRequest(
    @SerializedName("assigned_to")
    val assignedTo: String,
    val attachments: String? = null,
    val description: String,
    @SerializedName("due_date")
    val dueDate: String,
    @SerializedName("is_completed")
    val isCompleted: Boolean = false,
    val members: List<String>?,
    @SerializedName("owner_id")
    val ownerId: String,
    @SerializedName("project_id")
    val projectId: String,
    val title: String
)