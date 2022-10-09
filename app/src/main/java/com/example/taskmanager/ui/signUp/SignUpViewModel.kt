package com.example.taskmanager.ui.signUp

import androidx.lifecycle.*
import androidx.navigation.fragment.findNavController
import com.example.taskmanager.accounts.AccountsRepository
import com.example.taskmanager.dto.NetworkResult
import com.example.taskmanager.accounts.dto.SignUpResponseEntity
import com.example.taskmanager.accounts.entities.UserSettings
import com.example.taskmanager.accounts.settings.AppSettings
import com.example.taskmanager.dto.SignUpEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val repository: AccountsRepository,
    private val appSettings: AppSettings
): ViewModel() {

    private var _signUpResponse = MutableLiveData<NetworkResult<UserSettings>>()
        val signUpResponse: LiveData<NetworkResult<UserSettings>> = _signUpResponse

    //var repres = repository.signUp().asLiveData()

    //    init {
    //        signUp()
    //    }

    fun signUp(userInfo: SignUpEntity) {
        viewModelScope.launch {
            repository.signUp(userInfo).collect {
                //todo add try/catch for exceptions
                _signUpResponse.postValue(it)
                if (it is  NetworkResult.Success)  {
                        //todo add success
                    appSettings.createAccount(it.data)
                }
            }

        }
    }

}