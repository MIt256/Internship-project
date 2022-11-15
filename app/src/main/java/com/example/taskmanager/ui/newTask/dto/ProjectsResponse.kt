package com.example.taskmanager.ui.newTask.dto

import com.example.taskmanager.ui.newTask.entities.Project
import com.google.gson.annotations.SerializedName

data class ProjectsResponse(
    val `data`: List<ProjectData>
) {
    fun toProjectList(): List<Project> = data.map { it.toProject() }
}

data class ProjectData(
    @SerializedName("color")
    val color: String,
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("id")
    val id: String,
    @SerializedName("owner_id")
    val ownerId: String,
    @SerializedName("title")
    val title: String
) {
    /**
     * Convert this entity into in-app [Project] instance.
     */
    fun toProject(): Project {
        return Project(
            color = color,
            createdAt = createdAt,
            id = id,
            ownerId = ownerId,
            title = title
        )
    }
}