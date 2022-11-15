package com.example.taskmanager.room.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.example.taskmanager.accounts.entities.UserSettings
import com.example.taskmanager.ui.task.entities.TaskMember


@Entity(
    tableName = "users",
    indices = [
        Index("email", unique = true)
    ]
)
data class UserDbEntity(
    @PrimaryKey(autoGenerate = false) val id: String,
    val email: String,
    val username: String,
    @ColumnInfo(name = "avatar_path") val avatarPath: String?,
    @ColumnInfo(name = "created_at") val createdAt: String
) {
    fun toUserSettings() = TaskMember(
        avatarUrl = avatarPath,
        email = email,
        id = id,
        username = username,
        createdAt = createdAt
    )
}
