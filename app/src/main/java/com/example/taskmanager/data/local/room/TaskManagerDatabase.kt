package com.example.taskmanager.data.local.room

import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.taskmanager.data.local.dao.ProjectDao
import com.example.taskmanager.data.local.dao.QuickDao
import com.example.taskmanager.data.local.dao.TaskDao
import com.example.taskmanager.data.local.dao.UserDao
import com.example.taskmanager.data.local.entities.*

@Database(
    version = 3,
    entities = [
        UserDbEntity::class,
        TaskDbEntity::class,
        ProjectDbEntity::class,
        QuickNoteDbEntity::class,
        TaskMemberCrossRef::class
    ],
    autoMigrations = [
        AutoMigration(from = 2, to = 3)
    ]


)
abstract class TaskManagerDatabase : RoomDatabase() {
    abstract fun getTaskDao(): TaskDao
    abstract fun getUserDao(): UserDao
    abstract fun getProjectDao(): ProjectDao
    abstract fun getQuickNotesDao(): QuickDao
}