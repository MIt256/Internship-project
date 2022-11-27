package com.example.taskmanager.ui.task

import com.example.taskmanager.accounts.settings.AppSettings
import com.example.taskmanager.dto.NetworkResult
import com.example.taskmanager.room.TaskManagerDatabase
import com.example.taskmanager.room.dao.TaskDao
import com.example.taskmanager.ui.task.entities.Task
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class TaskRepository @Inject constructor(private val tasksApi: TasksApi, private val settings: AppSettings, private val database: TaskManagerDatabase) {

    suspend fun fetchTasks() = flow {
        emit(NetworkResult.Loading(true))
        val response = tasksApi.fetchUserTasks(settings.getCurrentId())
        response.toTaskList()
        emit(NetworkResult.Success(response.toTaskList()))
    }.catch { e ->
        emit(NetworkResult.Failure(e.message ?: "Unknown Error"))
    }

    suspend fun getTasksFromLocalDb() = flow {
        emit(NetworkResult.Loading(true))
        val response = database.getTaskDao().getUserTasks(settings.getCurrentId())
        if (response != null) {
            emit(NetworkResult.Success(response.map { it.toTask() }))
        }
    }.catch { e ->
        emit(NetworkResult.Failure(e.message ?: "Unknown Error"))
    }

    fun getTasks(): Flow<List<Task>> {
        try {
            val response =database.getTaskDao().getTasks(settings.getCurrentId())
            return response.map {
                it.map {
                    it?.toTask() ?: throw Exception("Error")
                }
            }
        } catch (ex:Exception){
            throw ex
        }
    }
}