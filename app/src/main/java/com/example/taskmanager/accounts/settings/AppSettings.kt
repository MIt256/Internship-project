package com.example.taskmanager.accounts.settings

import com.example.taskmanager.accounts.entities.UserSettings

interface AppSettings {

    fun getCurrentToken():String?

    fun getCurrentId():String?

    fun setCurrentToken(token: String?)

    fun createAccount(accountSettings: UserSettings)

}