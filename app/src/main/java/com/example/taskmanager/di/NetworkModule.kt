package com.example.taskmanager.di

import com.example.taskmanager.accounts.AccountsApi
import com.example.taskmanager.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Singleton
    @Provides
    fun provideOkHttp() : OkHttpClient{
        return OkHttpClient.Builder()
            .build()
    }

    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://todolist.dev2.cogniteq.com/api/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
    }

    @Provides
    fun provideApiClient(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }

    @Provides
    fun provideApiAccountClient(retrofit: Retrofit): AccountsApi {
        return retrofit.create(AccountsApi::class.java)
    }

}