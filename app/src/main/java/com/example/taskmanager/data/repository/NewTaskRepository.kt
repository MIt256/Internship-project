package com.example.taskmanager.data.repository

import com.example.taskmanager.data.local.dao.TaskDao
import com.example.taskmanager.data.local.entities.TaskDbEntity
import com.example.taskmanager.data.local.room.TaskManagerDatabase
import com.example.taskmanager.data.settings.AppSettings
import com.example.taskmanager.data.remote.api.NewTaskApi
import com.example.taskmanager.ui.entities.NewTask
import com.example.taskmanager.ui.entities.Project
import com.example.taskmanager.ui.task.entities.Task
import com.example.taskmanager.ui.task.entities.User
import kotlinx.coroutines.flow.*
import java.lang.Exception
import javax.inject.Inject

class NewTaskRepository @Inject constructor(private val newTaskApi: NewTaskApi, private val settings: AppSettings,private val database: TaskManagerDatabase) {

    suspend fun searchTaskMembers(query: String) = flow {
        val response = newTaskApi.taskMembersSearch(query)
        emit(response.toTaskMemberList())
    }.catch {
        throw Exception("Unknown Error")
    }

    suspend fun searchTaskMembersLocal(query: String):  Flow<List<User>> {
        val response = database.getUserDao().getAllUsers()
        return response.map {
            it.map{
                it?.toUserSettings() ?: throw Exception("Error")
            }
        }
    }

    suspend fun searchProjects(query: String) = flow {
        val response = newTaskApi.projectsSearch(query)
        emit(response.toProjectList())
    }.catch {
        throw Exception("Unknown Error")
    }

    suspend fun searchProjectsLocal(query: String): Flow<List<Project>>{
        val response = database.getProjectDao().getProjects(settings.getCurrentId())
        return  response.map {
                it.map {
                    it?.toProject() ?: throw Exception("Error")
                }
            }
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

    suspend fun createNewTaskLocal(task: TaskDbEntity) {
        database.getTaskDao().addTask(task)
    }

}