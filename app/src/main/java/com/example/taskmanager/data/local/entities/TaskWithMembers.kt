package com.example.taskmanager.data.local.entities

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.example.taskmanager.ui.task.entities.Task
import com.example.taskmanager.utils.DateTimeFormatterUtil
import java.time.LocalDateTime

data class TaskWithMembers(
    @Embedded val task: TaskDbEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "id",
        associateBy = Junction(
            TaskMemberCrossRef::class,
            parentColumn = "task_id",
            entityColumn = "user_id"
        )
    )
    val members: List<UserDbEntity>?
) {
    fun toTask() = Task(
        assignedTo = task.assignedTo,
        attachments = null,
        createdAt = task.createdAt,
        description = task.description,
        dueDate = DateTimeFormatterUtil.toLocalDateTime(task.dueDate),
        id = task.id,
        isCompleted = task.isCompleted,
        members = members?.map { it.toUserSettings() },
        ownerId = task.ownerId,
        projectId = task.projectId,
        title = task.title
    )
}

