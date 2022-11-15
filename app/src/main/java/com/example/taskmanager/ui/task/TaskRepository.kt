package com.example.taskmanager.ui.task

import com.example.taskmanager.accounts.settings.AppSettings
import com.example.taskmanager.dto.NetworkResult
import com.example.taskmanager.room.dao.TasksDao
import com.example.taskmanager.room.dao.UsersDao
import com.example.taskmanager.ui.task.entities.Task
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class TaskRepository @Inject constructor(private val tasksApi: TasksApi, private val settings: AppSettings, private val tasksDao: TasksDao, private val usersDao: UsersDao) {

    suspend fun fetchTasks() = flow {
        emit(NetworkResult.Loading(true))
        val response = tasksApi.fetchUserTasks(settings.getCurrentId())
        response.toTaskList()
        emit(NetworkResult.Success(response.toTaskList()))
    }.catch { e ->
        emit(NetworkResult.Failure(e.message ?: "Unknown Error"))
    }

    suspend fun fetchTasksAndSave() {
        val response = tasksApi.fetchUserTasks(settings.getCurrentId())
        addTasksAndUsersToLocalDb(response.toTaskList())
    }

    suspend fun addTasksAndUsersToLocalDb(tasks: List<Task>) {
        tasksDao.addAllTasks(tasks.map { it.toTaskDbEntity() })
        tasks.map {
            usersDao.addAllUsers(it.toUserDbList() ?: return)
        }
    }


//    suspend fun getTasksFromLocalDb() = flow {
//        emit(NetworkResult.Loading(true))
//        val response = tasksDao.getTasksWithMembers(settings.getCurrentId())
//        if (response != null) {
//            emit(NetworkResult.Success(response.map { it }))
//        }
//    }.catch { e ->
//        emit(NetworkResult.Failure(e.message ?: "Unknown Error"))
//    }

}