package com.example.taskmanager.ui.task

import com.example.taskmanager.ui.task.entities.Task
import com.example.taskmanager.ui.task.entities.TaskAttachment
import com.example.taskmanager.ui.task.entities.TaskMember
import com.google.gson.annotations.SerializedName

data class UserTasksResponse(
    val `data`: List<TasksData>
) {
    /**
     * Convert this entity into in-app [Tasks] instance.
     */
    fun toTaskList(): List<Task> {
        val tasks = ArrayList<Task>()
        data.forEach { tasks.add(it.toTask()) }
        return tasks
    }
}

data class OneTaskResponse(
    val `data`: TasksData
) {
    fun toTask(): Task = data.toTask()
}

data class TasksData(
    @SerializedName("assigned_to")
    val assignedTo: String?,
    @SerializedName("attachments")
    val attachments: List<Attachment>?,
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("due_date")
    val dueDate: String,
    @SerializedName("id")
    val id: String,
    @SerializedName("is_completed")
    val isCompleted: Boolean,
    @SerializedName("members")
    val members: List<Member>?,
    @SerializedName("owner_id")
    val ownerId: String,
    @SerializedName("project_id")
    val projectId: String,
    @SerializedName("title")
    val title: String
) {
    /**
     * Convert this entity into in-app [Task] instance.
     */
    fun toTask(): Task {
        val tmpAttachment = ArrayList<TaskAttachment>()
        attachments?.forEach { tmpAttachment.add(it.toTaskAttachment()) }
        val tmpMember = ArrayList<TaskMember>()
        members?.forEach { tmpMember.add(it.toTaskMember()) }
        return Task(
            assignedTo = assignedTo,
            attachments = tmpAttachment,
            createdAt = createdAt,
            description = description,
            dueDate = dueDate,
            id = id,
            isCompleted = isCompleted,
            members = tmpMember,
            ownerId = ownerId,
            projectId = projectId,
            title = title
        )
    }
}

data class Attachment(
    @SerializedName("created_at")
    val createdAt: String,
    val id: String,
    @SerializedName("task_id")
    val taskId: String,
    val type: String,
    val url: String
) {
    /**
     * Convert this entity into in-app [TaskAttachment] instance.
     */
    fun toTaskAttachment(): TaskAttachment = TaskAttachment(
        createdAt = createdAt,
        id = id,
        taskId = taskId,
        type = type,
        url = url
    )
}

data class Member(
    @SerializedName("avatar_url")
    val avatarUrl: String,
    @SerializedName("created_at")
    val createdAt: String,
    val email: String,
    val id: String,
    val username: String
) {
    /**
     * Convert this entity into in-app [TaskMember] instance.
     */
    fun toTaskMember(): TaskMember = TaskMember(
        avatarUrl = avatarUrl,
        createdAt = createdAt,
        email = email,
        id = id,
        username = username
    )
}