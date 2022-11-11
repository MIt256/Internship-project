package com.example.taskmanager.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.taskmanager.room.dao.ProjectsDao
import com.example.taskmanager.room.dao.TasksDao
import com.example.taskmanager.room.dao.UsersDao
import com.example.taskmanager.room.entities.ProjectDbEntity
import com.example.taskmanager.room.entities.TaskDbEntity
import com.example.taskmanager.room.entities.TaskMemberDbEntity
import com.example.taskmanager.room.entities.UserDbEntity

@Database(
    version = 1,
    entities = [
        UserDbEntity::class,
        TaskDbEntity::class,
        ProjectDbEntity::class,
        TaskMemberDbEntity::class
    ]
)
abstract class TaskManagerDatabase : RoomDatabase() {

    abstract fun getUsersDao(): UsersDao

    abstract fun getTasksDao(): TasksDao

    abstract fun getProjectsDao(): ProjectsDao
}