package com.example.taskmanager.di.module

import com.example.taskmanager.data.remote.api.AccountsApi
import com.example.taskmanager.data.settings.AppSettings
import com.example.taskmanager.data.remote.api.ProjectApi
import com.example.taskmanager.data.remote.api.NewTaskApi
import com.example.taskmanager.data.remote.api.TasksApi
import com.example.taskmanager.data.remote.api.UserApi
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideClient(settings: AppSettings): OkHttpClient {
        return OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .addInterceptor(createAuthorizationInterceptor(settings))
            .addInterceptor(createLoggingInterceptor())
            .build()
    }

    private fun createLoggingInterceptor(): Interceptor {
        return HttpLoggingInterceptor()
            .setLevel(HttpLoggingInterceptor.Level.BODY)
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
        val gson = GsonBuilder().setDateFormat("YYYY-MM-dd'T'hh:mm:ss.ssssss").serializeNulls().create()
        return Retrofit.Builder()
            .baseUrl("https://todolist.dev2.cogniteq.com/api/v1/")
            .addConverterFactory(GsonConverterFactory.create(gson))
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

    @Provides
    @Singleton
    fun provideApiUserClient(retrofit: Retrofit): UserApi {
        return retrofit.create(UserApi::class.java)
    }

    @Provides
    @Singleton
    fun provideApiProjectClient(retrofit: Retrofit): ProjectApi {
        return retrofit.create(ProjectApi::class.java)
    }

}