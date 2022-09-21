package com.example.taskmanager.accounts

import com.example.taskmanager.accounts.entities.*
import com.example.taskmanager.entities.NetworkResult
import com.example.taskmanager.entities.SignUpEntity
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
//todo add local api
class AccountsRepository  @Inject constructor(private val accountsApi: AccountsApi){

   suspend fun signUp(userInfo: SignUpEntity)  = flow {
        emit(NetworkResult.Loading(true))
        val requestEntity = SignUpRequestEntity(
            username = userInfo.username,
            email = userInfo.email,
            password = userInfo.password
        )
        //val response = accountsApi.signUp(requestEntity)
        //or
        val response = SignUpResponseEntity(SignUpData( "url","TestEmail@gmail.com","123",
           UserSession("token",123,"token refresh","type"), "username"))
        emit(NetworkResult.Success(response))
    }.catch { e ->
        emit(NetworkResult.Failure(e.message ?: "Unknown Error"))
    }

}