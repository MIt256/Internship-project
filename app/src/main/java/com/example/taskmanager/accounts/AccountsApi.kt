package com.example.taskmanager.accounts

import com.example.taskmanager.accounts.entities.SignInRequestEntity
import com.example.taskmanager.accounts.entities.SignInResponseEntity
import retrofit2.http.Body
import retrofit2.http.POST

interface AccountsApi {

    @POST("sign-in")
    suspend fun signIn(@Body body: SignInRequestEntity): SignInResponseEntity

    @POST("sign-up")
    suspend fun signUp(@Body body: SignInRequestEntity): SignInResponseEntity
}