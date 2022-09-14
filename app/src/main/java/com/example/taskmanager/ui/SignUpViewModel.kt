package com.example.taskmanager.ui

import androidx.lifecycle.ViewModel
import com.example.taskmanager.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(repository: Repository): ViewModel() {

}