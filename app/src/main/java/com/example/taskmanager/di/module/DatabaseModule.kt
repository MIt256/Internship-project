package com.example.taskmanager.di.module

import android.content.Context
import androidx.room.Room
import com.example.taskmanager.data.local.room.TaskManagerDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DatabaseModule {

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext appContext: Context): TaskManagerDatabase {
        return Room.databaseBuilder(
            appContext,
            TaskManagerDatabase::class.java,
            "database.db"
        ).fallbackToDestructiveMigration().build()
    }
}