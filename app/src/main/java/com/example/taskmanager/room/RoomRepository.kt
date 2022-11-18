package com.example.taskmanager.room

import android.util.Log
import com.example.taskmanager.accounts.settings.AppSettings
import com.example.taskmanager.room.dao.RoomDao
import com.example.taskmanager.ui.menu.ProjectApi
import com.example.taskmanager.ui.task.TasksApi
import com.example.taskmanager.users.UserApi
import javax.inject.Inject

class RoomRepository @Inject constructor(
    private val roomDao: RoomDao,
    private val tasksApi: TasksApi,
    private val userApi: UserApi,
    private val settings: AppSettings,
    private val projectApi: ProjectApi
) {

    suspend fun fetchTasksAndSave() {
        val response = tasksApi.fetchUserTasks(settings.getCurrentId())
        val tasks = response.toTaskList()
        tasks.forEach {
            //add members
            Log.e("Error", "$it")
            it.toUserDbList()?.forEach { roomDao.addUser(it) }
            Log.e("Error", "members")
            //add task owner and assigned user
            roomDao.addUser(userApi.fetchUser(it.ownerId).data.toUserDbEntity())
            Log.e("Error", "owner")
            if (it.assignedTo != null)
                roomDao.addUser(userApi.fetchUser(it.assignedTo).data.toUserDbEntity())
            Log.e("Error", "assigned")
            //add project and project owner
            val project = projectApi.fetchProject(it.projectId).data.toProjectDbEntity()
            roomDao.addUser(userApi.fetchUser(project.ownerId).data.toUserDbEntity())
            Log.e("Error", "Project owner")
            roomDao.addProject(project)
            Log.e("Error", "Project")
            //add task
            roomDao.addTask(it.toTaskDbEntity())
            Log.e("Error", "task")
            //add crossRef
            it.toTaskMemberCrossRefList()?.forEach { roomDao.addTaskMemberCrossRef(it) }

            Log.e("Error", "Success")
        }
    }
}