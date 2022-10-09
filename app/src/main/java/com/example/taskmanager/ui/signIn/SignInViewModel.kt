package com.example.taskmanager.ui.signIn

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.taskmanager.accounts.AccountsRepository
import com.example.taskmanager.accounts.dto.SignInResponseEntity
import com.example.taskmanager.accounts.entities.UserSettings
import com.example.taskmanager.accounts.settings.AppSettings
import com.example.taskmanager.dto.NetworkResult
import com.example.taskmanager.dto.SignInEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val repository: AccountsRepository,
    private val appSettings: AppSettings
) : ViewModel() {

    private var _signInResponse = MutableLiveData<NetworkResult<UserSettings>>()
    val signInResponse: LiveData<NetworkResult<UserSettings>> = _signInResponse

    fun signIn(userInfo: SignInEntity) {
        viewModelScope.launch {
            repository.signIn(userInfo).collect {
                //todo add try/catch for exceptions
                _signInResponse.postValue(it)
                if (it is NetworkResult.Success) {
                    //todo add success
                    appSettings.createAccount(it.data)
                }
            }

        }
    }

}