package com.example.taskmanager.data.remote.model.profile

import com.example.taskmanager.ui.entities.Statistic
import com.google.gson.annotations.SerializedName

data class StatisticResponse(
    val `data`: StatisticData
)

data class StatisticData(
    @SerializedName("completed_tasks")
    val completedTasks: String,
    @SerializedName("created_tasks")
    val createdTasks: String,
    @SerializedName("events")
    val events: String,
    @SerializedName("quick_notes")
    val quickNotes: String,
    @SerializedName("todo")
    val todo: String
) {
    fun toStatistic() = Statistic(
        createdTasks = createdTasks,
        completedTasks = completedTasks,
        events = events,
        quickNotes = quickNotes,
        todo = todo
    )
}