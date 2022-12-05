package com.example.taskmanager.data.remote.api

import com.example.taskmanager.ui.task.UserTasksResponse
import retrofit2.http.GET
import retrofit2.http.Path


interface TasksApi {

    @GET("user-tasks/{id}")
    suspend fun fetchUserTasks(@Path("id") userId: String): UserTasksResponse

}