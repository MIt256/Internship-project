package com.example.taskmanager.data.remote.api

import com.example.taskmanager.data.remote.model.qiucknote.NewQuickNoteRequest
import com.example.taskmanager.data.remote.model.qiucknote.OneQuickNoteResponse
import com.example.taskmanager.data.remote.model.qiucknote.QuickNoteResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface QuickApi {

    @POST("notes")
    suspend fun createQuickNote(@Body body: NewQuickNoteRequest): OneQuickNoteResponse

    @GET("user-notes/{user_id}")
    suspend fun fetchUserQuickNotes(@Path("user_id") userId: String): QuickNoteResponse
}