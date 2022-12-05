package com.example.taskmanager.data.repository

import com.example.taskmanager.data.settings.AppSettings
import com.example.taskmanager.data.local.room.TaskManagerDatabase
import com.example.taskmanager.data.remote.api.ProjectApi
import com.example.taskmanager.data.remote.model.project.NewProjectRequest
import com.example.taskmanager.ui.entities.Project
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ProjectsRepository @Inject constructor(private val database: TaskManagerDatabase, private val projectApi: ProjectApi, private val settings: AppSettings) {
    fun getProjects(): Flow<List<Project>> {
        try {
            val response = database.getProjectDao().getProjects(settings.getCurrentId())
            return response.map {
                it.map {
                    it?.toProject() ?: throw Exception("Error")
                }
            }
        } catch (ex: Exception) {
            throw ex
        }
    }

    suspend fun addNewProject(title: String, color: String)  {
        val newProject = NewProjectRequest(
            title = title,
            color = color,
            ownerId = settings.getCurrentId()
        )
        try {
            val response = projectApi.createProject(newProject)
            database.getProjectDao().addProject(response.data.toProjectDbEntity())
            throw Exception("Success")
        } catch (e: Exception) {
            throw e
        }
    }
}