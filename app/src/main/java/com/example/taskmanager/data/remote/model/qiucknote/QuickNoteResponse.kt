package com.example.taskmanager.data.remote.model.qiucknote

import androidx.room.ColumnInfo
import com.example.taskmanager.data.local.entities.QuickNoteDbEntity
import com.example.taskmanager.ui.entities.QuickNote
import com.google.gson.annotations.SerializedName

data class QuickNoteResponse(
    val `data`: List<QuickNoteData>
)

data class OneQuickNoteResponse(
    val `data`: QuickNoteData
)

data class QuickNoteData(
    @SerializedName("id")
    val id: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("color")
    val color: String,
    @SerializedName("tilte")
    val title: String,
    @SerializedName("owner_id")
    val ownerId: String,
    @SerializedName("is_completed")
    val isCompleted: Boolean,
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("date_start")
    val dateStart: String?,
    @SerializedName("time_start")
    val timeStart: String?,
    @SerializedName("date_end")
    val dateEnd: String?,
    @SerializedName("time_end")
    val timeEnd: String?
) {
    fun toQuick() = QuickNote(
        id = id,
        description = description,
        color = color,
        title = title,
        ownerId = ownerId,
        dateStart = dateStart,
        timeStart = timeStart,
        dateEnd = dateEnd,
        timeEnd = timeEnd,
        isCompleted = isCompleted,
        createdAt = createdAt
    )

    fun toDbQuick() = QuickNoteDbEntity(
        id = id,
        description = description,
        color = color,
        title = title,
        dateStart = dateStart,
        timeStart = timeStart,
        dateEnd = dateEnd,
        timeEnd = timeEnd,
        ownerId = ownerId,
        isCompleted = isCompleted,
        createdAt = createdAt
    )
}
