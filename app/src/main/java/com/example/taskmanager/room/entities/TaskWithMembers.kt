package com.example.taskmanager.room.entities

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation

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
)
