package com.example.taskmanager.ui.menu

import com.example.taskmanager.ui.menu.dto.ProjectResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface ProjectApi {

    @GET("projects/{id}")
    suspend fun fetchProject(@Path("id") projectId: String): ProjectResponse

}