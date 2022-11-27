package com.example.taskmanager.room.entities

import androidx.room.Embedded
import androidx.room.Relation

data class TaskWithFullInfo(
    @Embedded val task: TaskDbEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "ownerId"
    )
    val owner: UserDbEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "assigned_to"
    )
    val assignedTo: UserDbEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "project_id"
    )
    val project: ProjectDbEntity
)
