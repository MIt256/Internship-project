package com.example.taskmanager.di

import com.example.taskmanager.accounts.settings.AccountManagerAppSettings
import com.example.taskmanager.accounts.settings.AppSettings
import com.example.taskmanager.accounts.settings.SharedPrefAppSettings
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class SettingsModule {

    @Binds
    abstract fun bindAppSettings(
        //appSettings: SharedPrefAppSettings
        appSettings: AccountManagerAppSettings
    ): AppSettings
}