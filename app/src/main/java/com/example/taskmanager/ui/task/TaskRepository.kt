package com.example.taskmanager.ui.task

import com.example.taskmanager.accounts.settings.AppSettings
import com.example.taskmanager.dto.NetworkResult
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class TaskRepository @Inject constructor(private val tasksApi: TasksApi, private val settings:AppSettings) {

    suspend fun fetchTasks() = flow {
        emit(NetworkResult.Loading(true))
        val response = tasksApi.fetchUserTasks(settings.getCurrentId())
        emit(NetworkResult.Success(response.toTaskList()))
    }.catch { e ->
        emit(NetworkResult.Failure(e.message ?: "Unknown Error"))
    }

}