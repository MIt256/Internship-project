package com.example.taskmanager.room

import android.util.Log
import com.example.taskmanager.accounts.settings.AppSettings
import com.example.taskmanager.room.entities.ProjectDbEntity
import com.example.taskmanager.room.entities.TaskMemberCrossRef
import com.example.taskmanager.room.entities.UserDbEntity
import com.example.taskmanager.ui.menu.ProjectApi
import com.example.taskmanager.ui.task.TasksApi
import com.example.taskmanager.ui.task.entities.Task
import com.example.taskmanager.users.UserApi
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
        val dbProjects = tasks.map { projectApi.fetchProject(it.projectId).data.toProjectDbEntity() }
        val dbUserProjects = projectApi.fetchUserProjects(settings.getCurrentId()).data.map { it.toProjectDbEntity() }
        dbProjects.plus(dbUserProjects)
        val dpUsers = fetchAllUsers(tasks, dbProjects)

        try {
            database.runInTransaction(Runnable {
                try {
                    database.getUserDao().addAllUsers(dpUsers)
                    database.getProjectDao().addAllProjects(dbProjects)
                    database.getTaskDao().addAllTasks(dbTasks)
                    database.getUserDao().addAllTaskMemberCrossRefs(dbTaskMemberCrossRefs)
                } catch (ex: Exception) {
                    ex.message?.let { Log.e("Error", it) }
                    throw ex
                }
                try {
                    database.getProjectDao().addAllProjects(dbUserProjects)
                } catch (ex: Exception) {
                    ex.message?.let { Log.e("Error", it) }
                    throw ex
                }
            })
        } catch (ex: Exception) {
            ex.message?.let { Log.e("Error", it) }
        }

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

        userIdSet.add(tasks[0].ownerId)
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