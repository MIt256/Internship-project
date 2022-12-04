package com.example.taskmanager.data.remote.model.qiuck

import com.example.taskmanager.data.local.entities.QuickDbEntity
import com.example.taskmanager.ui.entities.Quick
import com.google.gson.annotations.SerializedName

data class QuickResponse(
    val `data`: List<QuickData>
)

data class OneQuickResponse(
    val `data`: QuickData
)

data class QuickData(
    @SerializedName("id")
    val id: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("color")
    val color: String,
    @SerializedName("owner_id")
    val ownerId: String,
    @SerializedName("is_completed")
    val isCompleted: Boolean,
    @SerializedName("created_at")
    val createdAt: String
) {
    fun toQuick() = Quick(
        id = id,
        description = description,
        color = color,
        ownerId = ownerId,
        isCompleted = isCompleted,
        createdAt = createdAt
    )

    fun toDbQuick() = QuickDbEntity(
        id = id,
        description = description,
        color = color,
        ownerId = ownerId,
        isCompleted = isCompleted,
        createdAt = createdAt
    )
}
