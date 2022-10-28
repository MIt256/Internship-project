package com.example.taskmanager.ui.newTask.entities

import com.example.taskmanager.ui.newTask.dto.NewTaskRequest
import com.example.taskmanager.ui.task.entities.TaskAttachment
import com.example.taskmanager.ui.task.entities.TaskMember
import java.util.*
import kotlin.collections.ArrayList

data class NewTask(
    val assignedTo: String,
    val description: String,
    val dueDate: Date,
    val members: List<TaskMember>?,
    var ownerId: String,
    val projectId: String,
    val title: String
) {
    fun toNewTaskRequest(): NewTaskRequest {
        val tmpMembersId = ArrayList<String>()
        members?.forEach { tmpMembersId.add(it.id) }
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