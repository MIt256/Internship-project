package com.example.taskmanager.accounts

import com.example.taskmanager.accounts.entities.*
import retrofit2.http.Body
import retrofit2.http.POST

interface AccountsApi {

    @POST("sign-in")
    suspend fun signIn(@Body body: SignInRequestEntity): SignInResponseEntity

    @POST("sign-up")
    suspend fun signUp(@Body body: SignUpRequestEntity): SignUpResponseEntity

    @POST("sign-out")
    suspend fun signUp(@Body body: SignOutRequest): SignOutResponse

    @POST("refresh-token")
    suspend fun signUp(@Body body: RefreshTokenRequest): RefreshTokenResponse

}