package com.example.taskmanager

import javax.inject.Inject

class Repository  @Inject constructor(private val apiService: ApiService){

//    suspend fun getPopularMovies()  = flow {
//        emit(NetworkResult.Loading(true))
//        val response = apiService.getMostPopularMovies()
//        emit(NetworkResult.Success(response.items))
//    }.catch { e ->
//        emit(NetworkResult.Failure(e.message ?: "Unknown Error"))
//    }

}