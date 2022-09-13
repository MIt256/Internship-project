package com.example.taskmanager

import com.example.taskmanager.network.ApiService
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class Repository  @Inject constructor(private val apiService: ApiService, appSettings: AppSettings){

//    suspend fun getPopularMovies()  = flow {
//        emit(NetworkResult.Loading(true))
//        val response = apiService.getMostPopularMovies()
//        emit(NetworkResult.Success(response.items))
//    }.catch { e ->
//        emit(NetworkResult.Failure(e.message ?: "Unknown Error"))
//    }
}