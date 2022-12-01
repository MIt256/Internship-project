package com.example.taskmanager.room.entities

import androidx.room.*
import com.example.taskmanager.ui.newTask.entities.Project

@Entity(
    tableName = "projects",
    foreignKeys = [
        ForeignKey(
            entity = UserDbEntity::class,
            parentColumns = ["id"],
            childColumns = ["owner_id"],
            onDelete = ForeignKey.CASCADE,
            onUpdate = ForeignKey.NO_ACTION,
            deferred = true
        )
    ],
    indices = [
        Index("owner_id")
    ]
)
data class ProjectDbEntity(
    @PrimaryKey(autoGenerate = false) val id: String,
    val title: String,
    val color: String,
    @ColumnInfo(name = "owner_id") val ownerId: String,
    @ColumnInfo(name = "created_at") val createdAt: String
) {
    fun toProject() = Project(
        id = id,
        title = title,
        color = color,
        ownerId = ownerId,
        createdAt = createdAt
    )
}