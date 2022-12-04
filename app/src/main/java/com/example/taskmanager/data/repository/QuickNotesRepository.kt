package com.example.taskmanager.data.repository

import com.example.taskmanager.data.local.room.TaskManagerDatabase
import com.example.taskmanager.data.settings.AppSettings
import com.example.taskmanager.ui.entities.Quick
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class QuickNotesRepository @Inject constructor(private val database: TaskManagerDatabase, private val settings: AppSettings) {

    fun getQuickNotes(): Flow<List<Quick>> {
        try {
            val response = database.getQuickNotesDao().getQuicks(settings.getCurrentId())
            return response.map {
                it.map {
                    it?.toQuick() ?: throw Exception("Error, cant get quick notes")
                }
            }
        } catch (ex: Exception) {
            throw ex
        }
    }
}