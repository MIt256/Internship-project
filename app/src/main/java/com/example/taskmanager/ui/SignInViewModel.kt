package com.example.taskmanager.ui

import androidx.lifecycle.ViewModel
import com.example.taskmanager.Repository
import com.example.taskmanager.accounts.AccountsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(repository: AccountsRepository): ViewModel() {

}