package com.example.taskmanager.accounts

import javax.inject.Inject

class AccountsRepository  @Inject constructor(private val accountsApi: AccountsApi){

    //    suspend fun getPopularMovies()  = flow {
//        emit(NetworkResult.Loading(true))
//        val response = apiService.getMostPopularMovies()
//        emit(NetworkResult.Success(response.items))
//    }.catch { e ->
//        emit(NetworkResult.Failure(e.message ?: "Unknown Error"))
//    }

}