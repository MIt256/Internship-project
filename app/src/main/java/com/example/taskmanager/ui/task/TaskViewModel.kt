package com.example.taskmanager.ui.task

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.taskmanager.dto.NetworkResult
import com.example.taskmanager.ui.task.entities.Task
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TaskViewModel @Inject constructor(private val repository: TaskRepository) : ViewModel() {

    private var _tasks = MutableLiveData<NetworkResult<List<Task>>>()
    val tasks: LiveData<NetworkResult<List<Task>>> = _tasks

    init {
        fetchUserTasks()
    }

    private fun fetchUserTasks() {
        viewModelScope.launch {
            //todo add id
            repository.fetchTasks("76d2fab4-fd06-4909-bf8e-875c6b55c1f7").collect {
                //todo add try/catch for exceptions
                _tasks.postValue(it)
            }

        }
    }

}