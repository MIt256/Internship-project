package com.example.taskmanager

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.taskmanager.accounts.settings.AppSettings
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class UserSharedViewModel @Inject constructor(
    private val appSettings: AppSettings,
) : ViewModel() {

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

}