package com.example.taskmanager.ui.menu

import com.example.taskmanager.accounts.settings.AppSettings
import com.example.taskmanager.room.dao.RoomDao
import com.example.taskmanager.room.entities.ProjectDbEntity
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ProjectsRepository @Inject constructor(private val roomDao: RoomDao, private val projectApi: ProjectApi, private val settings: AppSettings) {
    suspend fun getProjects() = flow {
        var response = listOf<ProjectDbEntity>()
        try {
            response = roomDao.getProjects(settings.getCurrentId())
        } catch (ex: Exception) {
            throw ex
        }
        emit(response.map { it.toProject() })
    }
}