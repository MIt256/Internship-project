package com.example.taskmanager.data.repository

import com.example.taskmanager.data.remote.api.AccountsApi
import com.example.taskmanager.data.remote.model.accounts.SignInRequestEntity
import com.example.taskmanager.data.remote.model.accounts.SignUpRequestEntity
import com.example.taskmanager.data.remote.utils.NetworkResult
import com.example.taskmanager.data.remote.model.accounts.SignInEntity
import com.example.taskmanager.data.remote.model.accounts.SignUpEntity
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import java.util.*
import javax.inject.Inject

//todo add local api
class AccountsRepository @Inject constructor(private val accountsApi: AccountsApi) {

    suspend fun signUp(userInfo: SignUpEntity) = flow {
        emit(NetworkResult.Loading(true))
        val requestEntity = SignUpRequestEntity(
            username = userInfo.username,
            email = userInfo.email,
            password = Base64.getEncoder().encodeToString(userInfo.password.toByteArray())
        )
        val response = accountsApi.signUp(
            SignUpRequestEntity(
                username = "andreikastsiuk",
                email = "andrei.kastsiuk@cogniteq.com",
                password = "MTIzNDU2Nzhx"
            )
        )
        emit(NetworkResult.Success(response.data.toUserSettings()))
    }.catch { e ->
        emit(NetworkResult.Failure(e.message ?: "Unknown Error"))
    }

    suspend fun signIn(userInfo: SignInEntity) = flow {
        emit(NetworkResult.Loading(true))
        val requestEntity = SignInRequestEntity(
            email = userInfo.email,
            password = Base64.getEncoder().encodeToString(userInfo.password.toByteArray())
        )
        val response = accountsApi.signIn(
            SignInRequestEntity(
                email = "andrei.kastsiuk@cogniteq.com",
                password = "MTIzNDU2Nzhx"
            )
        )
        emit(NetworkResult.Success(response.data.toUserSettings(userInfo.email)))
    }.catch { e ->
        emit(NetworkResult.Failure(e.message ?: "Unknown Error"))
    }

    suspend fun signOut() {
        //todo implement signOut
    }

    suspend fun refreshToken() {
        //todo implement refreshToken function
    }
}