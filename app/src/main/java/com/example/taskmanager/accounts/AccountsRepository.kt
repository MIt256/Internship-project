package com.example.taskmanager.accounts

import com.example.taskmanager.accounts.dto.*
import com.example.taskmanager.dto.NetworkResult
import com.example.taskmanager.dto.SignInEntity
import com.example.taskmanager.dto.SignUpEntity
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
        //or        //todo delete this code
        val response = SignUpResponseEntity(SignUpData( "url","TestEmail@gmail.com","123",
           UserSession("token",123,"token refresh","type"), "username"))
        emit(NetworkResult.Success(response.data.toUserSettings()))
    }.catch { e ->
        emit(NetworkResult.Failure(e.message ?: "Unknown Error"))
    }

    suspend fun signIn(userInfo: SignInEntity)  = flow {
        emit(NetworkResult.Loading(true))
        val requestEntity = SignInRequestEntity(
            email = userInfo.email,
            password = userInfo.password
        )
        val response = accountsApi.signIn(requestEntity)
        emit(NetworkResult.Success(response))
    }.catch { e ->
        emit(NetworkResult.Failure(e.message ?: "Unknown Error"))
    }

    suspend fun signOut(){
        //todo implement signOut
    }

    suspend fun refreshToken(){
        //todo implement refreshToken function
    }
}