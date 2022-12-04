package com.example.taskmanager.data.local.room

import android.util.Log
import com.example.taskmanager.data.local.entities.TaskMemberCrossRef
import com.example.taskmanager.data.local.entities.UserDbEntity
import com.example.taskmanager.data.remote.api.ProjectApi
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
    private val projectApi: ProjectApi
) {
    suspend fun fetchTasksAndSave() {
        val response = tasksApi.fetchUserTasks(settings.getCurrentId())
        val tasks = response.toTaskList()

        val dbTasks = tasks.map { it.toTaskDbEntity() }
        val dbTaskMemberCrossRefs = getAllCrossRefs(tasks)
        val dbUserProjects = projectApi.fetchUserProjects(settings.getCurrentId()).data.map { it.toProjectDbEntity() }
        val dpUsers = fetchAllUsers(tasks)


        database.runInTransaction(Runnable {
            try {
                database.getUserDao().addAllUsers(dpUsers)
                database.getProjectDao().addAllProjects(dbUserProjects)
                database.getTaskDao().addAllTasks(dbTasks)
                database.getUserDao().addAllTaskMemberCrossRefs(dbTaskMemberCrossRefs)
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

    private suspend fun fetchAllUsers(tasks: List<Task>): List<UserDbEntity> {
        val users = mutableSetOf<UserDbEntity>()
        val userIdSet = mutableSetOf<String>()

        userIdSet.add(tasks[0].ownerId)
        tasks.forEach {
            val members = it.toUserDbList()
            if (members != null) {
                users.addAll(members)
            }
            if (it.assignedTo != null)
                userIdSet += it.assignedTo
        }

        userIdSet.forEach {
            users.add(userApi.fetchUser(it).data.toUserDbEntity())
        }
        return users.toList()
    }
}