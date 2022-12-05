package com.example.taskmanager.data.local.room

import android.util.Log
import com.example.taskmanager.data.local.entities.ProjectDbEntity
import com.example.taskmanager.data.local.entities.TaskMemberCrossRef
import com.example.taskmanager.data.local.entities.UserDbEntity
import com.example.taskmanager.data.remote.api.ProjectApi
import com.example.taskmanager.data.remote.api.QuickApi
import com.example.taskmanager.data.remote.api.TasksApi
import com.example.taskmanager.data.remote.api.UserApi
import com.example.taskmanager.data.settings.AppSettings
import com.example.taskmanager.ui.task.entities.Task
import javax.inject.Inject

class SyncRepository @Inject constructor(
    private val database: TaskManagerDatabase,
    private val tasksApi: TasksApi,
    private val userApi: UserApi,
    private val settings: AppSettings,
    private val projectApi: ProjectApi,
    private val quickApi: QuickApi,
) {
    suspend fun fetchTasksAndSave() {
        val response = tasksApi.fetchUserTasks(settings.getCurrentId())
        val tasks = response.toTaskList()

        val dbTasks = tasks.map { it.toTaskDbEntity() }
        val dbTaskMemberCrossRefs = getAllCrossRefs(tasks)
        val dbProjects = tasks.map { projectApi.fetchProject(it.projectId).data.toProjectDbEntity() }
            .plus(projectApi.fetchUserProjects(settings.getCurrentId()).data.map { it.toProjectDbEntity() })
        val dpUsers = fetchAllUsers(tasks, dbProjects)
        val dpQuicks = quickApi.fetchUserQuickNotes(settings.getCurrentId()).data.map { it.toDbQuick() }

        database.runInTransaction(Runnable {
            try {
                database.getUserDao().addAllUsers(dpUsers)
                database.getProjectDao().addAllProjects(dbProjects)
                database.getTaskDao().addAllTasks(dbTasks)
                database.getUserDao().addAllTaskMemberCrossRefs(dbTaskMemberCrossRefs)
                database.getQuickNotesDao().addAllQuickNotes(dpQuicks)
            } catch (ex: Exception) {
                Log.d("SyncWorker", "Error while adding in db: ${ex.message}")
                throw ex
            }
        })

    }

    private fun getAllCrossRefs(tasks: List<Task>): List<TaskMemberCrossRef> {
        val refs = mutableListOf<TaskMemberCrossRef>()
        tasks.forEach {
            val tempRefs = it.toTaskMemberCrossRefList()
            if (tempRefs != null) {
                refs.addAll(tempRefs)
            }
        }
        return refs
    }

    private suspend fun fetchAllUsers(tasks: List<Task>, projects: List<ProjectDbEntity>): List<UserDbEntity> {
        val users = mutableSetOf<UserDbEntity>()
        val userIdSet = mutableSetOf<String>()

        userIdSet.add(settings.getCurrentId())
        tasks.forEach {
            val members = it.toUserDbList()
            if (members != null) {
                users.addAll(members)
            }
            if (it.assignedTo != null)
                userIdSet += it.assignedTo
        }
        projects.forEach { userIdSet += it.ownerId }
        userIdSet.forEach {
            users.add(userApi.fetchUser(it).data.toUserDbEntity())
        }
        return users.toList()
    }
}