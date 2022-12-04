package com.example.taskmanager.data.remote.api

import com.example.taskmanager.data.remote.model.qiuck.NewQuickRequest
import com.example.taskmanager.data.remote.model.qiuck.OneQuickResponse
import com.example.taskmanager.data.remote.model.qiuck.QuickResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface QuickApi {

    @POST("notes")
    suspend fun createQuickNote(@Body body: NewQuickRequest): OneQuickResponse

    @GET("user-notes/{user_id}")
    suspend fun fetchUserQuickNotes(@Path("user_id") userId: String): QuickResponse
}