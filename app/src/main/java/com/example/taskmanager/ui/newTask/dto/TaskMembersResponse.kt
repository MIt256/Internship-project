package com.example.taskmanager.ui.newTask.dto

import com.example.taskmanager.ui.task.Member
import com.example.taskmanager.ui.task.entities.TaskMember

data class TaskMembersResponse(
    val `data`: List<Member>
) {
    /**
     * Convert this entity into in-app [TaskMember] instance.
     */
    fun toTaskMemberList(): List<TaskMember> = data.map { it.toTaskMember() }
}
