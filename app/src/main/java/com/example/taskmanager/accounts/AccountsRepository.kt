package com.example.taskmanager.accounts

import com.example.taskmanager.accounts.entities.NetworkResult
import com.example.taskmanager.accounts.entities.SignUpRequestEntity
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
//todo add local api
class AccountsRepository  @Inject constructor(private val accountsApi: AccountsApi){

    suspend fun signUp()  = flow {
        emit(NetworkResult.Loading(true))
        val response = accountsApi.signUp(SignUpRequestEntity("email","password","username"))
        emit(NetworkResult.Success(response))
    }.catch { e ->
        emit(NetworkResult.Failure(e.message ?: "Unknown Error"))
    }

}