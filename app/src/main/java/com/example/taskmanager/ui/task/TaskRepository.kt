package com.example.taskmanager.ui.task

import com.example.taskmanager.dto.NetworkResult
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class TaskRepository @Inject constructor(private val tasksApi: TasksApi) {

    suspend fun fetchTasks(userId: String) = flow{
        emit(NetworkResult.Loading(true))
        val response = tasksApi.fetchUserTasks(userId)
        emit(NetworkResult.Success(response.toTaskList()))
    }.catch { e ->
        emit(NetworkResult.Failure(e.message ?: "Unknown Error"))
    }

}