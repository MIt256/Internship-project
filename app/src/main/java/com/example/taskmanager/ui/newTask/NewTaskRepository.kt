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
        val response = newTaskApi.taskMembersSearch(query)
        emit(response.toTaskMemberList())
    }.catch {
        throw Exception("Unknown Error")
    }

    suspend fun searchProjects(query: String) = flow {
        val response = newTaskApi.projectsSearch(query)
        emit(response.toProjectList())
    }.catch {
        throw Exception("Unknown Error")
    }

    suspend fun createNewTask(task: NewTask) = flow {
        try {
            task.ownerId = settings.getCurrentId()
        } catch (e: Exception) {
            throw Exception(e)
        }
        try {
            val response = newTaskApi.createTask(task.toNewTaskRequest())
            //todo add to local db
            emit("Success")
        } catch (e: Exception) {
            throw Exception("Unknown Error")
        }
    }

}