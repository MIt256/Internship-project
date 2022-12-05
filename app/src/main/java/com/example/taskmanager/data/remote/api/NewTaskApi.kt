package com.example.taskmanager.data.remote.api

import com.example.taskmanager.data.remote.model.newtask.ProjectsResponse
import com.example.taskmanager.data.remote.model.newtask.TaskMembersResponse
import com.example.taskmanager.data.remote.model.newtask.NewTaskRequest
import com.example.taskmanager.ui.task.OneTaskResponse
import retrofit2.http.*

interface NewTaskApi {

    @GET("task-members-search")
    suspend fun taskMembersSearch(@Query("query") query:String): TaskMembersResponse

    @GET("projects-search")
    suspend fun projectsSearch(@Query("query") query:String): ProjectsResponse

    @POST("tasks")
    suspend fun createTask(@Body body: NewTaskRequest): OneTaskResponse
}