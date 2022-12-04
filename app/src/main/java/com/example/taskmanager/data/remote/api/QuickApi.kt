package com.example.taskmanager.data.remote.api

import com.example.taskmanager.data.remote.model.qiuck.QuickResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface QuickApi {
//    todo add
//    @POST("projects")
//    suspend fun createProject(@Body body: NewProjectRequest): QuickResponse

    @GET("user-notes/{user_id}")
    suspend fun fetchUserQuickNotes(@Path("user_id") userId: String): QuickResponse
}