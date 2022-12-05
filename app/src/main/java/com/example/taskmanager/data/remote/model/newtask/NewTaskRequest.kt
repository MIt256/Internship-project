package com.example.taskmanager.data.remote.model.newtask

import com.google.gson.annotations.SerializedName
import java.util.*

data class NewTaskRequest(
    @SerializedName("assigned_to")
    val assignedTo: String,
    val attachments: String? = null,
    val description: String,
    @SerializedName("due_date")
    val dueDate: Date,
    @SerializedName("is_completed")
    val isCompleted: Boolean = false,
    val members: List<String>? = null,
    @SerializedName("owner_id")
    val ownerId: String,
    @SerializedName("project_id")
    val projectId: String,
    val title: String
)