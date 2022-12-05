package com.example.taskmanager.ui.entities

data class QuickNote(
    val id: String,
    val description: String,
    val color: String,
    val ownerId: String,
    val isCompleted: Boolean,
    val createdAt: String
)
