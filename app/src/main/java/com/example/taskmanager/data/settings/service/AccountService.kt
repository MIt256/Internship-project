package com.example.taskmanager.data.settings.service

import android.app.Service
import android.content.Intent
import android.os.IBinder
import com.example.taskmanager.data.settings.AccountAuth

class AccountService : Service() {
    override fun onBind(p0: Intent?): IBinder? {
        TODO("Not yet implemented")
        return AccountAuth(this).iBinder

    }

}