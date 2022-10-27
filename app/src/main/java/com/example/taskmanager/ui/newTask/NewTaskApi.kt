package com.example.taskmanager.ui.newTask

import com.example.taskmanager.ui.newTask.dto.ProjectsResponse
import com.example.taskmanager.ui.newTask.dto.TaskMembersResponse
import com.example.taskmanager.ui.task.UserTasksResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface NewTaskApi {

    @GET("task-members-search")
    suspend fun taskMembersSearch(@Query("query") query:String): TaskMembersResponse

    @GET("projects-search")
    suspend fun projectsSearch(@Query("query") query:String): ProjectsResponse

}