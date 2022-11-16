package com.example.taskmanager.ui.newTask

import androidx.lifecycle.ViewModel
import com.example.taskmanager.ui.task.TaskRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NewTaskViewModel @Inject constructor(private val repository: NewTaskRepository): ViewModel() {
    // TODO: Implement the ViewModel
}