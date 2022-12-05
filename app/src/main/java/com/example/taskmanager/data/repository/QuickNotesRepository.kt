package com.example.taskmanager.data.repository

import com.example.taskmanager.data.local.room.TaskManagerDatabase
import com.example.taskmanager.data.remote.api.QuickApi
import com.example.taskmanager.data.remote.model.qiucknote.NewQuickNoteRequest
import com.example.taskmanager.data.settings.AppSettings
import com.example.taskmanager.ui.entities.QuickNote
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class QuickNotesRepository @Inject constructor(private val database: TaskManagerDatabase, private val settings: AppSettings, private val quickApi: QuickApi) {

    fun getQuickNotes(): Flow<List<QuickNote>> {
        return database.getQuickNotesDao().getQuickNotes(settings.getCurrentId()).map {
            it.map {
                it?.toQuick() ?: throw Exception("Error, cant get quick notes")
            }
        }

    }

    suspend fun addNewQuick(description: String, color: String) {
        val newQuick = NewQuickNoteRequest(
            description = description,
            color = color,
            ownerId = settings.getCurrentId()
        )
        try {
            val response = quickApi.createQuickNote(newQuick)
            database.getQuickNotesDao().addQuickNote(response.noteData.toDbQuick())
        } catch (e: Exception) {
            throw e
        }
    }
}