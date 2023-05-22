package com.example.taskmanager.ui.task.entities

import com.example.taskmanager.data.local.entities.TaskDbEntity
import com.example.taskmanager.data.local.entities.TaskMemberCrossRef
import com.example.taskmanager.data.local.entities.TaskWithMembers
import com.example.taskmanager.data.local.entities.UserDbEntity
import com.example.taskmanager.utils.DateTimeFormatterUtil
import java.time.LocalDateTime

data class Task(
    val assignedTo: String?,
    val attachments: List<TaskAttachment>?,
    val createdAt: String,
    var description: String,
    val dueDate: LocalDateTime,
    val id: String,
    val isCompleted: Boolean,
    val members: List<User>?,
    val ownerId: String,
    val projectId: String,
    var title: String
) {
    fun toTaskWithMembersEntity() = TaskWithMembers(
        toTaskDbEntity(),
        toUserDbList()
    )

    fun toTaskMemberCrossRefList(): List<TaskMemberCrossRef>? {
        return members?.map { TaskMemberCrossRef(
            it.id,
            id
        ) }
    }

    fun toTaskDbEntity() = TaskDbEntity(
        id = id,
        title = title,
        dueDate = DateTimeFormatterUtil.formatter.format(dueDate),
        description = description,
        assignedTo = assignedTo,
        isCompleted = isCompleted,
        projectId = projectId,
        ownerId = ownerId,
        createdAt = createdAt
    )

    fun toUserDbList(): List<UserDbEntity>? {
        return members?.map { it.toUserDbEntity() }
    }
}

data class TaskAttachment(
    val createdAt: String,
    val id: String,
    val taskId: String,
    val type: String,
    val url: String
)

data class User(
    val avatarUrl: String?,
    val createdAt: String,
    val email: String,
    val id: String,
    val username: String
) {
    fun toUserDbEntity() = UserDbEntity(
        id = id,
        email = email,
        username = username,
        avatarPath = avatarUrl,
        createdAt = createdAt
    )
}