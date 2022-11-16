package com.example.taskmanager.ui.newTask

import com.example.taskmanager.ui.newTask.dto.ProjectsResponse
import com.example.taskmanager.ui.newTask.dto.TaskMembersResponse
import com.example.taskmanager.ui.newTask.dto.NewTaskRequest
import com.example.taskmanager.ui.task.OneTaskResponse
import com.example.taskmanager.ui.task.UserTasksResponse
import retrofit2.http.*

interface NewTaskApi {

    @GET("task-members-search")
    suspend fun taskMembersSearch(@Query("query") query:String): TaskMembersResponse

    @GET("projects-search")
    suspend fun projectsSearch(@Query("query") query:String): ProjectsResponse

    @POST("tasks")
    suspend fun createTask(@Body body: NewTaskRequest): OneTaskResponse
}