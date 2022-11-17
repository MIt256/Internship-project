package com.example.taskmanager.room.entities

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation

data class UserWithTasks(
    @Embedded val user: UserDbEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "id",
        associateBy = Junction(TaskMemberCrossRef::class)
    )
    val tasks: List<TaskDbEntity>
) {
}