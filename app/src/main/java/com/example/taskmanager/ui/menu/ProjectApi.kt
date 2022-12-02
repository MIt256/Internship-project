package com.example.taskmanager.ui.menu

import com.example.taskmanager.ui.menu.dto.NewProjectRequest
import com.example.taskmanager.ui.menu.dto.ProjectResponse
import com.example.taskmanager.ui.newTask.dto.NewTaskRequest
import com.example.taskmanager.ui.newTask.dto.ProjectsResponse
import com.example.taskmanager.ui.task.OneTaskResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ProjectApi {

    @GET("projects/{id}")
    suspend fun fetchProject(@Path("id") projectId: String): ProjectResponse

    @POST("projects")
    suspend fun createProject(@Body body: NewProjectRequest): ProjectResponse

    @GET("user-projects/{user_id}")
    suspend fun fetchUserProjects(@Path("user_id") userId: String): ProjectsResponse

}