package com.example.taskmanager

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.example.taskmanager.accounts.settings.AppSettings
import com.example.taskmanager.wm.SyncWorker
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

@HiltViewModel
class UserSharedViewModel @Inject constructor(
    private val appSettings: AppSettings,
    application: Application
) : AndroidViewModel(application) {

    private var _isAuthenticated = MutableLiveData<Boolean>()
    var isAuthenticated: LiveData<Boolean> = _isAuthenticated

    init{
        checkUserStatus()
        workManagerStart()
    }

    fun setStatus(status: Boolean){
        _isAuthenticated.value = status
    }

    private fun checkUserStatus() {
        _isAuthenticated.value = appSettings.checkUserToken()
    }

    private fun workManagerStart(){

        val workRequest =
            OneTimeWorkRequestBuilder<SyncWorker>().build()
        WorkManager.getInstance(getApplication()).enqueue(workRequest)

    }

}