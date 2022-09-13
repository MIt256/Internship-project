package com.example.taskmanager

interface AppSettings {

    fun getCurrentToken():String?

    fun setCurrentToken(token: String?)

}