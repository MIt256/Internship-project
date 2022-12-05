package com.example.taskmanager.data.local.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.example.taskmanager.ui.task.entities.User


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
    fun toUserSettings() = User(
        avatarUrl = avatarPath,
        email = email,
        id = id,
        username = username,
        createdAt = createdAt
    )
}
