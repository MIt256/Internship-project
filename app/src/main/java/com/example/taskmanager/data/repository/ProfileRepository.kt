package com.example.taskmanager.data.repository

import com.example.taskmanager.data.local.room.TaskManagerDatabase
import com.example.taskmanager.data.remote.api.UserApi
import com.example.taskmanager.data.settings.AppSettings
import com.example.taskmanager.ui.entities.ProfileStatisticItem
import com.example.taskmanager.ui.entities.Statistic
import com.example.taskmanager.ui.task.entities.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ProfileRepository @Inject constructor(private val database: TaskManagerDatabase, private val settings: AppSettings, private val userApi: UserApi) {

    fun getProfileInfo(): Flow<User> {
        return database.getUserDao().getUserById(settings.getCurrentId()).map {
            it?.toUserSettings() ?: throw Exception("Error, can't get user")
        }
    }

    fun getProfileWorkItems() = flow {
        emit(
            listOf(
                ProfileStatisticItem("Tasks", database.getTaskDao().getCountOfTasks(settings.getCurrentId()), "#6074F9"),
                ProfileStatisticItem("Periods", database.getQuickNotesDao().getCountOfNotes(settings.getCurrentId()), "#8560F9"),
                ProfileStatisticItem("Projects", database.getProjectDao().getCountOfProjects(settings.getCurrentId()), "#F96060")
            )
        )
    }

    suspend fun getProfileStatistic(): Statistic {
        val response = userApi.getUserStatistic(settings.getCurrentId())
        return response.data.toStatistic()
    }

}