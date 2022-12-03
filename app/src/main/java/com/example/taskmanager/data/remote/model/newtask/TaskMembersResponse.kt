package com.example.taskmanager.data.remote.model.newtask

import com.example.taskmanager.ui.task.UserData
import com.example.taskmanager.ui.task.entities.User

data class TaskMembersResponse(
    val `data`: List<UserData>
) {
    /**
     * Convert this entity into in-app [User] instance.
     */
    fun toTaskMemberList(): List<User> = data.map { it.toUser() }
}
