package com.example.taskmanager.di

import com.example.taskmanager.room.TaskManagerDatabase
import com.example.taskmanager.room.dao.ProjectsDao
import com.example.taskmanager.room.dao.TasksDao
import com.example.taskmanager.room.dao.UsersDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DatabaseModule {

    @Provides
    @Singleton
    fun provideUsersDao(appDatabase: TaskManagerDatabase): UsersDao {
        return appDatabase.getUsersDao()
    }

    @Provides
    @Singleton
    fun provideTasksDao(appDatabase: TaskManagerDatabase): TasksDao {
        return appDatabase.getTasksDao()
    }

    @Provides
    @Singleton
    fun provideProjectsDao(appDatabase: TaskManagerDatabase): ProjectsDao {
        return appDatabase.getProjectsDao()
    }

}