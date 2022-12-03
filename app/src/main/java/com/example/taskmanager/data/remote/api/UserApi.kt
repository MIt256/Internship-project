package com.example.taskmanager.data.remote.api

import com.example.taskmanager.data.remote.model.accounts.UserResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface UserApi {

    @GET("users/{id}")
    suspend fun fetchUser(@Path("id") userId: String): UserResponse

}