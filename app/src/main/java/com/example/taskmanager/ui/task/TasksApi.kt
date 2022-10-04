package com.example.taskmanager.ui.task

import retrofit2.http.GET
import retrofit2.http.Path


interface TasksApi {

    @GET("user-tasks/{id}")
    suspend fun fetchUserTasks(@Path("id") userId: String):UserTasksResponse

}