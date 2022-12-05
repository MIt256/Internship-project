package com.example.taskmanager.data.settings

import com.example.taskmanager.ui.entities.UserSettings

interface AppSettings {

    fun getCurrentToken():String?

    fun getCurrentId():String

    fun setCurrentToken(token: String?)

    fun createAccount(accountSettings: UserSettings)

    fun checkUserToken():Boolean

}