package com.example.taskmanager.accounts

import com.example.taskmanager.accounts.dto.*
import retrofit2.http.Body
import retrofit2.http.POST

interface AccountsApi {

    @POST("sign-in")
    suspend fun signIn(@Body body: SignInRequestEntity): SignInResponseEntity

    @POST("sign-up")
    suspend fun signUp(@Body body: SignUpRequestEntity): SignUpResponseEntity

    @POST("sign-out")
    suspend fun signOut(@Body body: SignOutRequest): SignOutResponse

    @POST("refresh-token")
    suspend fun refreshToken(@Body body: RefreshTokenRequest): RefreshTokenResponse
}