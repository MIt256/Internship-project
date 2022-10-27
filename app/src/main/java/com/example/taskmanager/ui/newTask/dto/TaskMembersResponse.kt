package com.example.taskmanager.ui.newTask.dto

import com.example.taskmanager.ui.task.Member
import com.example.taskmanager.ui.task.entities.Task
import com.example.taskmanager.ui.task.entities.TaskMember
import com.google.gson.annotations.SerializedName

data class TaskMembersResponse(
    val `data`: List<Member>
){
    /**
     * Convert this entity into in-app [TaskMember] instance.
     */
    fun toTaskMemberList(): List<TaskMember> {
        val members = ArrayList<TaskMember>()
        data.forEach { members.add(it.toTaskMember()) }
        return members
    }
}
