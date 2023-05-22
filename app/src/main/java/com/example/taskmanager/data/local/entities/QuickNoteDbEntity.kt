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
    val title:String?,
    val color: String,
    @ColumnInfo(name = "date_start") val dateStart: String?,
    @ColumnInfo(name = "time_start") val timeStart: String?,
    @ColumnInfo(name = "date_end") val dateEnd: String?,
    @ColumnInfo(name = "time_end") val timeEnd: String?,
    @ColumnInfo(name = "is_completed") val isCompleted: Boolean,
    @ColumnInfo(name = "owner_id") val ownerId: String,
    @ColumnInfo(name = "created_at") val createdAt: String
) {
    fun toQuick() = QuickNote(
        id = id,
        title = title,
        description = description,
        color = color,
        ownerId = ownerId,
        dateStart = dateStart,
        timeStart = timeStart,
        dateEnd = dateEnd,
        timeEnd = timeEnd,
        isCompleted = isCompleted,
        createdAt = createdAt
    )
}