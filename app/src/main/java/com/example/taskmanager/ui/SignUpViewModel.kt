package com.example.taskmanager.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.taskmanager.Repository
import com.example.taskmanager.accounts.AccountsRepository
import com.example.taskmanager.accounts.entities.NetworkResult
import com.example.taskmanager.accounts.entities.SignUpResponseEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(private val repository: AccountsRepository): ViewModel() {

    private var _signUpResponse = MutableLiveData<NetworkResult<SignUpResponseEntity>>()
    val signUpResponse: LiveData<NetworkResult<SignUpResponseEntity>> = _signUpResponse

    init {
        signUp()
    }

    private fun signUp() {
        viewModelScope.launch {
            repository.signUp().collect {
                //todo ?
                _signUpResponse.value
            }
        }
    }

}