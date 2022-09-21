package com.example.taskmanager.ui

import androidx.lifecycle.*
import com.example.taskmanager.accounts.AccountsRepository
import com.example.taskmanager.entities.NetworkResult
import com.example.taskmanager.accounts.entities.SignUpResponseEntity
import com.example.taskmanager.entities.SignUpEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(private val repository: AccountsRepository): ViewModel() {

    private var _signUpResponse = MutableLiveData<NetworkResult<SignUpResponseEntity>>()
        val signUpResponse: LiveData<NetworkResult<SignUpResponseEntity>> = _signUpResponse

    //var repres = repository.signUp().asLiveData()

//    init {
//        signUp()
//    }

    fun signUp(userInfo: SignUpEntity) {
        viewModelScope.launch {
            repository.signUp(userInfo).collect {
                //todo add try/catch for exceptions
                _signUpResponse.postValue(it)
            }

        }
    }

}