package com.example.taskmanager.room

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.example.taskmanager.room.entities.TaskDbEntity
import com.example.taskmanager.room.entities.TaskMemberDbEntity
import com.example.taskmanager.room.entities.UserDbEntity

data class TaskWithMembersTuple(
    @Embedded
    val task: TaskDbEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "id",
        associateBy = Junction(
            value = TaskMemberDbEntity::class,
            parentColumn = "task_id",
            entityColumn = "User_id"
        )

    )
    val members: List<UserDbEntity>
) {

}