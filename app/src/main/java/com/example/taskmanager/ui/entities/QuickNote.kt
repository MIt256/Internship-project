package com.example.taskmanager.ui.entities

data class QuickNote(
    val id: String,
    val title: String?,
    val description: String,
    val color: String,
    val ownerId: String,
    val dateStart: String?,
    val timeStart: String?,
    val dateEnd: String?,
    val timeEnd: String?,
    val isCompleted: Boolean,
    val createdAt: String
)
