package com.example.taskmanager.ui.menu

import com.example.taskmanager.accounts.settings.AppSettings
import com.example.taskmanager.room.TaskManagerDatabase
import com.example.taskmanager.ui.newTask.entities.Project
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
}