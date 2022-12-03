package com.example.taskmanager.data.local.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index

@Entity(
    tableName = "task_members",
    primaryKeys = ["user_id","task_id"],
    foreignKeys = [
        ForeignKey(
            entity = UserDbEntity::class,
            parentColumns = ["id"],
            childColumns = ["user_id"],
            onDelete = ForeignKey.CASCADE,
            onUpdate = ForeignKey.CASCADE,
            deferred = true
        ),
        ForeignKey(
            entity = TaskDbEntity::class,
            parentColumns = ["id"],
            childColumns = ["task_id"],
            onDelete = ForeignKey.CASCADE,
            onUpdate = ForeignKey.CASCADE,
            deferred = true
        )
    ],
    indices = [
        Index("task_id"),
        Index("user_id")
    ]
)
data class TaskMemberCrossRef(
    @ColumnInfo(name = "user_id") val userId: String,
    @ColumnInfo(name = "task_id") val taskId: String
) {
    //todo add functions
}