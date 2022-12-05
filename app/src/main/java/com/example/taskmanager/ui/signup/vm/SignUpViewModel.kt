package com.example.taskmanager.ui.signup.vm

import androidx.lifecycle.*
import com.example.taskmanager.data.repository.AccountsRepository
import com.example.taskmanager.data.remote.utils.NetworkResult
import com.example.taskmanager.ui.entities.UserSettings
import com.example.taskmanager.data.settings.AppSettings
import com.example.taskmanager.data.remote.model.accounts.SignUpEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val repository: AccountsRepository,
    private val appSettings: AppSettings
) : ViewModel() {

    private var _signUpResponse = MutableLiveData<NetworkResult<UserSettings>>()
    val signUpResponse: LiveData<NetworkResult<UserSettings>> = _signUpResponse

    //var repres = repository.signUp().asLiveData()

    fun signUp(userInfo: SignUpEntity) {
        viewModelScope.launch {
            repository.signUp(userInfo).collect {
                //todo add try/catch for exceptions
                _signUpResponse.postValue(it)
                if (it is NetworkResult.Success) {
                    //todo add success
                    appSettings.createAccount(it.data)
                }
            }

        }
    }

}