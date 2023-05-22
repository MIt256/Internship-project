package com.example.taskmanager.data.repository

import com.example.taskmanager.data.local.entities.QuickNoteDbEntity
import com.example.taskmanager.data.local.room.TaskManagerDatabase
import com.example.taskmanager.data.remote.api.QuickApi
import com.example.taskmanager.data.remote.model.qiucknote.NewQuickNoteRequest
import com.example.taskmanager.data.settings.AppSettings
import com.example.taskmanager.ui.entities.QuickNote
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import kotlin.random.Random

class QuickNotesRepository @Inject constructor(private val database: TaskManagerDatabase, private val settings: AppSettings, private val quickApi: QuickApi) {

    fun getQuickNotes(): Flow<List<QuickNote>> {
        return database.getQuickNotesDao().getQuickNotes(settings.getCurrentId()).map {
            it.map {
                it?.toQuick() ?: throw Exception("Error, cant get quick notes")
            }
        }

    }

    suspend fun addNewQuick(title: String,description: String, color: String,sd: String,st: String,ed: String,et: String) {
        val newQuick = QuickNoteDbEntity(
            title = title,
            id = Random.nextInt(0,99999999).toString(),
            description = description,
            color = color,
            ownerId = settings.getCurrentId(),
            createdAt = "2023-05-21T07:11:03.089950",
            timeStart = st,
            isCompleted = false,
            dateStart = sd,
            dateEnd = ed,
            timeEnd = et
        )
        //val response = quickApi.createQuickNote(newQuick)
        database.getQuickNotesDao().addQuickNote(newQuick)
    }
}