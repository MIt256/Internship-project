package com.example.taskmanager.room

import com.example.taskmanager.accounts.settings.AppSettings
import com.example.taskmanager.room.dao.RoomDao
import com.example.taskmanager.room.entities.ProjectDbEntity
import com.example.taskmanager.room.entities.TaskMemberCrossRef
import com.example.taskmanager.room.entities.UserDbEntity
import com.example.taskmanager.ui.menu.ProjectApi
import com.example.taskmanager.ui.task.TasksApi
import com.example.taskmanager.ui.task.entities.Task
import com.example.taskmanager.users.UserApi
import javax.inject.Inject

class SyncRepository @Inject constructor(
    private val roomDao: RoomDao,
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
        val dpUsers = fetchAllUsers(tasks, dbProjects)

        roomDao.addAllSyncInfo(dbTasks, dbTaskMemberCrossRefs, dbProjects, dpUsers)
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