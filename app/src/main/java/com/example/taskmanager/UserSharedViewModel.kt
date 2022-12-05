package com.example.taskmanager

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.example.taskmanager.data.settings.AppSettings
import com.example.taskmanager.workmanager.SyncWorker
import dagger.hilt.android.lifecycle.HiltViewModel
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
    }

    fun setStatus(status: Boolean){
        _isAuthenticated.value = status
    }

    private fun checkUserStatus() {
        _isAuthenticated.value = appSettings.checkUserToken()
    }

    fun workManagerStart(){

        val workRequest =
            OneTimeWorkRequestBuilder<SyncWorker>().build()
        WorkManager.getInstance(getApplication()).enqueue(workRequest)

    }

}