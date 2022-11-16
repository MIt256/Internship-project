package com.example.taskmanager.di

import com.example.taskmanager.accounts.AccountsApi
import com.example.taskmanager.accounts.settings.AppSettings
import com.example.taskmanager.ui.newTask.NewTaskApi
import com.example.taskmanager.ui.task.TasksApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideClient(settings: AppSettings): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(createAuthorizationInterceptor(settings))
            .build()
    }

    private fun createAuthorizationInterceptor(settings: AppSettings): Interceptor {
        return Interceptor { chain ->
            val newBuilder = chain.request().newBuilder()
            //todo add token
            val token = settings.getCurrentToken().toString()
            if (token != null) {
                newBuilder.addHeader("Authorization", "Bearer $token")
            }
            return@Interceptor chain.proceed(newBuilder.build())
        }
    }

    @Provides
    @Singleton
    fun provideRetrofit(client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://todolist.dev2.cogniteq.com/api/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }


    @Provides
    @Singleton
    fun provideApiAccountClient(retrofit: Retrofit): AccountsApi {
        return retrofit.create(AccountsApi::class.java)
    }

    @Provides
    @Singleton
    fun provideApiTasksClient(retrofit: Retrofit): TasksApi {
        return retrofit.create(TasksApi::class.java)
    }

    @Provides
    @Singleton
    fun provideApiNewTaskClient(retrofit: Retrofit): NewTaskApi {
        return retrofit.create(NewTaskApi::class.java)
    }


}