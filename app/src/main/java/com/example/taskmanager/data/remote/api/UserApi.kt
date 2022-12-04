package com.example.taskmanager.data.remote.api

import com.example.taskmanager.data.remote.model.accounts.UserResponse
import com.example.taskmanager.data.remote.model.profile.StatisticResponse
import com.example.taskmanager.ui.entities.Statistic
import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET
import retrofit2.http.Path

interface UserApi {

    @GET("users/{id}")
    suspend fun fetchUser(@Path("id") userId: String): UserResponse

    @GET("users-statistics/{id}")
    suspend fun getUserStatistic(@Path("id") userId: String): StatisticResponse

}