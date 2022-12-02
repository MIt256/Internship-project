package com.example.taskmanager.room.entities

import androidx.room.*
import com.example.taskmanager.ui.task.entities.Task

@Entity(
    tableName = "tasks",
    foreignKeys = [
        ForeignKey(
            entity = UserDbEntity::class,
            parentColumns = ["id"],
            childColumns = ["assigned_to"],
            onDelete = ForeignKey.CASCADE,
            onUpdate = ForeignKey.CASCADE,
            deferred = true
        ),
        ForeignKey(
            entity = UserDbEntity::class,
            parentColumns = ["id"],
            childColumns = ["owner_id"],
            onDelete = ForeignKey.CASCADE,
            onUpdate = ForeignKey.CASCADE,
            deferred = true
        ),
        ForeignKey(
            entity = ProjectDbEntity::class,
            parentColumns = ["id"],
            childColumns = ["project_id"],
            onDelete = ForeignKey.CASCADE,
            onUpdate = ForeignKey.CASCADE,
            deferred = true
        )
    ],
    indices = [
        Index("owner_id"),
        Index("assigned_to"),
        Index("project_id")
    ]
)
data class TaskDbEntity(
    @PrimaryKey(autoGenerate = false) val id: String,
    val title: String,
    @ColumnInfo(name = "due_date") val dueDate: String,
    val description: String,
    @ColumnInfo(name = "assigned_to") val assignedTo: String?,
    @ColumnInfo(name = "is_completed") val isCompleted: Boolean,
    @ColumnInfo(name = "project_id") val projectId: String,
    @ColumnInfo(name = "owner_id") val ownerId: String,
    @ColumnInfo(name = "created_at") val createdAt: String
) {
    fun toTask() = Task(
        id = id,
        assignedTo = assignedTo,
        attachments = null,
        createdAt = createdAt,
        description = description,
        dueDate = dueDate,
        isCompleted = isCompleted,
        members = null,
        ownerId = ownerId,
        projectId = projectId,
        title = title
    )

}
