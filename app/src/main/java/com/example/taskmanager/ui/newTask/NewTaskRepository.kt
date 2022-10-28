package com.example.taskmanager.ui.newTask

import com.example.taskmanager.accounts.settings.AppSettings
import com.example.taskmanager.dto.NetworkResult
import com.example.taskmanager.ui.newTask.dto.NewTaskRequest
import com.example.taskmanager.ui.newTask.entities.NewTask
import com.example.taskmanager.ui.task.TasksApi
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import java.lang.Exception
import javax.inject.Inject

class NewTaskRepository @Inject constructor(private val newTaskApi: NewTaskApi,private val settings: AppSettings) {

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

    suspend fun createNewTask(task: NewTask) = flow {
        try{
            task.ownerId = settings.getCurrentId()
        } catch (e:Exception){
            throw Exception(e)
        }
        emit(NetworkResult.Loading(true))
        val response = newTaskApi.createTask(task.toNewTaskRequest())
        emit(NetworkResult.Success(response.toTaskList()))
    }.catch { e ->
        emit(NetworkResult.Failure(e.message ?: "Unknown Error"))
    }

}