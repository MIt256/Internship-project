package com.example.taskmanager.users

import com.example.taskmanager.users.dto.UserResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface UserApi {

    @GET("users/{id}")
    suspend fun fetchUser(@Path("id") userId: String): UserResponse

}