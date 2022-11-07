package com.example.taskmanager.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.taskmanager.room.entities.UserDbEntity

@Database(
    version = 1,
    entities = [UserDbEntity::class]
)
abstract class TaskManagerDatabase : RoomDatabase() {

    abstract fun getUsersDao(): UsersDao


}