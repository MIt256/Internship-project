package com.example.taskmanager.ui.newTask.entities

data class Project(
    val color: String,
    val createdAt: String,
    val id: String,
    val ownerId: String,
    val title: String
)