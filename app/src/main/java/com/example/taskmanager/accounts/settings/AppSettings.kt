package com.example.taskmanager.accounts.settings

interface AppSettings {

    fun getCurrentToken():String?

    fun setCurrentToken(token: String?)

}