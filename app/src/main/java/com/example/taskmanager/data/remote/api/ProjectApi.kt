package com.example.taskmanager.data.remote.api

import com.example.taskmanager.data.remote.model.project.NewProjectRequest
import com.example.taskmanager.data.remote.model.project.ProjectResponse
import com.example.taskmanager.data.remote.model.newtask.ProjectsResponse
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