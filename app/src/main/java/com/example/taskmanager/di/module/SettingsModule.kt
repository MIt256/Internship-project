package com.example.taskmanager.di.module

import com.example.taskmanager.data.settings.AccountManagerAppSettings
import com.example.taskmanager.data.settings.AppSettings
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class SettingsModule {

    @Binds
    abstract fun bindAppSettings(
        appSettings: AccountManagerAppSettings
    ): AppSettings
}