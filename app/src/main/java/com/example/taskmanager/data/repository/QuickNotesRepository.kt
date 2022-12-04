package com.example.taskmanager.data.repository

import com.example.taskmanager.data.local.room.TaskManagerDatabase
import com.example.taskmanager.data.remote.api.QuickApi
import com.example.taskmanager.data.remote.model.project.NewProjectRequest
import com.example.taskmanager.data.remote.model.qiuck.NewQuickRequest
import com.example.taskmanager.data.settings.AppSettings
import com.example.taskmanager.ui.entities.Quick
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class QuickNotesRepository @Inject constructor(private val database: TaskManagerDatabase, private val settings: AppSettings, private val quickApi: QuickApi) {

    fun getQuickNotes(): Flow<List<Quick>> {
        try {
            val response = database.getQuickNotesDao().getQuickNotes(settings.getCurrentId())
            return response.map {
                it.map {
                    it?.toQuick() ?: throw Exception("Error, cant get quick notes")
                }
            }
        } catch (ex: Exception) {
            throw ex
        }
    }

    suspend fun addNewQuick(description: String, color: String)  {
        val newQuick = NewQuickRequest(
            description = description,
            color = color,
            ownerId = settings.getCurrentId()
        )
        try {
            val response = quickApi.createQuickNote(newQuick)
            database.getQuickNotesDao().addQuickNote(response.data.toDbQuick())
            throw Exception("Success")
        } catch (e: Exception) {
            throw e
        }
    }
}