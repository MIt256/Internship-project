package com.example.taskmanager.room

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.example.taskmanager.room.entities.TaskDbEntity
import com.example.taskmanager.room.entities.TaskMemberCrossRef
import com.example.taskmanager.room.entities.UserDbEntity

data class TaskWithMembersTuple(
    @Embedded
    val task: TaskDbEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "id",
        associateBy = Junction(
            value = TaskMemberCrossRef::class,
            parentColumn = "task_id",
            entityColumn = "user_id"
        )

    )
    val members: List<UserDbEntity>?
) {

}