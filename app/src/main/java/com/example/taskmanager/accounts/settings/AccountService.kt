package com.example.taskmanager.accounts.settings

import android.app.Service
import android.content.Intent
import android.os.IBinder

class AccountService : Service() {
    override fun onBind(p0: Intent?): IBinder? {
        TODO("Not yet implemented")
        return AccountAuth(this).iBinder

    }

}