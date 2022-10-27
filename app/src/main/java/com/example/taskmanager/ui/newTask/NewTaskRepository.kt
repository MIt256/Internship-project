package com.example.taskmanager.ui.newTask

import com.example.taskmanager.dto.NetworkResult
import com.example.taskmanager.ui.task.TasksApi
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class NewTaskRepository @Inject constructor(private val newTaskApi: NewTaskApi) {

    suspend fun searchTaskMembers(query: String) = flow {
        emit(NetworkResult.Loading(true))
        val response = newTaskApi.taskMembersSearch(query)
        emit(NetworkResult.Success(response.toTaskMemberList()))
    }.catch { e ->
        emit(NetworkResult.Failure(e.message ?: "Unknown Error"))
    }

    suspend fun searchProjects(query: String) = flow {
        emit(NetworkResult.Loading(true))
        val response = newTaskApi.projectsSearch(query)
        emit(NetworkResult.Success(response.toProjectList()))
    }.catch { e ->
        emit(NetworkResult.Failure(e.message ?: "Unknown Error"))
    }

}