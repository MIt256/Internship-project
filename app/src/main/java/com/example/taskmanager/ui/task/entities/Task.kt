package com.example.taskmanager.ui.task.entities

data class Task(
    val assignedTo: String?,
    val attachments: List<TaskAttachment>,
    val createdAt: String,
    val description: String,
    val dueDate: String,
    val id: String,
    val isCompleted: Boolean,
    val members: List<TaskMember>,
    val ownerId: String,
    val projectId: String,
    val title: String
)

data class TaskAttachment(
    val createdAt: String,
    val id: String,
    val taskId: String,
    val type: String,
    val url: String
)

data class TaskMember(
    val avatarUrl: String,
    val createdAt: String,
    val email: String,
    val id: String,
    val username: String
)