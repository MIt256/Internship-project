package com.example.taskmanager.room.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.example.taskmanager.accounts.entities.UserSettings


@Entity(
    tableName = "users",
    indices = [
        Index("email", unique = true)
    ]
)
data class UserDbEntity(
    @PrimaryKey(autoGenerate = true) val id: Long,
    val email: String,
    val username: String,
    @ColumnInfo(name = "avatar_path") val avatarPath: String,
    @ColumnInfo(name = "created_at") val createdAt: String,
) {
    //todo add mappers
    fun toUserSettings() = UserSettings(
        avatarUrl = avatarPath,
        email = email,
        id = id.toString(),
        username = username,
        accessToken = null,
        expiresIn = null,
        refreshToken = null,
        tokenType = null
    )
}
