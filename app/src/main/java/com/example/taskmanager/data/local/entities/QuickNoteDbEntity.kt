package com.example.taskmanager.data.local.entities

import androidx.room.*
import com.example.taskmanager.ui.entities.QuickNote

@Entity(
    tableName = "quicks",
    foreignKeys = [
        ForeignKey(
            entity = UserDbEntity::class,
            parentColumns = ["id"],
            childColumns = ["owner_id"],
            onDelete = ForeignKey.CASCADE,
            onUpdate = ForeignKey.CASCADE,
            deferred = true
        )
    ],
    indices = [
        Index("owner_id")
    ]
)
data class QuickNoteDbEntity(
    @PrimaryKey(autoGenerate = false) val id: String,
    val description: String,
    val color: String,
    @ColumnInfo(name = "is_completed") val isCompleted: Boolean,
    @ColumnInfo(name = "owner_id") val ownerId: String,
    @ColumnInfo(name = "created_at") val createdAt: String
) {
    fun toQuick() = QuickNote(
        id = id,
        description = description,
        color = color,
        ownerId = ownerId,
        isCompleted = isCompleted,
        createdAt = createdAt
    )
}