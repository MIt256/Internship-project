package com.example.taskmanager.ui.newTask.entities

import com.example.taskmanager.ui.newTask.dto.NewTaskRequest
import com.example.taskmanager.ui.task.entities.User
import java.util.*

data class NewTask(
    val assignedTo: String,
    val description: String,
    val dueDate: Date,
    val members: List<User>?,
    var ownerId: String,
    val projectId: String,
    val title: String
) {
    fun toNewTaskRequest(): NewTaskRequest {
        val tmpMembersId = members?.map { it.id }
        return NewTaskRequest(
            assignedTo = assignedTo,
            description = description,
            dueDate = dueDate,
            members = if (tmpMembersId.isNullOrEmpty()) null else tmpMembersId,
            ownerId = ownerId,
            projectId = projectId,
            title = title
        )
    }
}