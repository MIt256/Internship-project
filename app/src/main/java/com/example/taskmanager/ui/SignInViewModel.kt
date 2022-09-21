package com.example.taskmanager.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.taskmanager.accounts.AccountsRepository
import com.example.taskmanager.accounts.entities.SignInResponseEntity
import com.example.taskmanager.entities.NetworkResult
import com.example.taskmanager.entities.SignInEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(private val repository: AccountsRepository): ViewModel() {

    private var _signInResponse = MutableLiveData<NetworkResult<SignInResponseEntity>>()
    val signInResponse: LiveData<NetworkResult<SignInResponseEntity>> = _signInResponse

    fun signIn(userInfo: SignInEntity) {
        viewModelScope.launch {
            repository.signIn(userInfo).collect {
                //todo add try/catch for exceptions
                _signInResponse.postValue(it)
            }

        }
    }

}