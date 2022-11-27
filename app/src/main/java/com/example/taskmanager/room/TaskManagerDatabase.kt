package com.example.taskmanager.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.taskmanager.room.dao.ProjectDao
import com.example.taskmanager.room.dao.TaskDao
import com.example.taskmanager.room.dao.UserDao
import com.example.taskmanager.room.entities.ProjectDbEntity
import com.example.taskmanager.room.entities.TaskDbEntity
import com.example.taskmanager.room.entities.TaskMemberCrossRef
import com.example.taskmanager.room.entities.UserDbEntity

@Database(
    version = 1,
    entities = [
        UserDbEntity::class,
        TaskDbEntity::class,
        ProjectDbEntity::class,
        TaskMemberCrossRef::class
    ]
)
abstract class TaskManagerDatabase : RoomDatabase() {
    abstract fun getTaskDao(): TaskDao
    abstract fun getUserDao(): UserDao
    abstract fun getProjectDao(): ProjectDao
}