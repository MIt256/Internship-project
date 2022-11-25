package com.example.taskmanager.ui.task

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.taskmanager.dto.NetworkResult
import com.example.taskmanager.ui.task.entities.Task
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class TaskViewModel @Inject constructor(private val repository: TaskRepository) : ViewModel() {

    var currentException = MutableSharedFlow<String>()

    private var _tasks = MutableLiveData<List<Task>>()
    val tasks: LiveData<List<Task>> = _tasks

    fun setException(exception: String) =
        viewModelScope.launch { currentException.emit(exception) }

    fun fetchTasks() {
        viewModelScope.launch {
            try {
                repository.getTasks().collect {
                    _tasks.postValue(it)
                }
            } catch (exception:Exception){
                exception.message?.let { setException(it) }
            }
        }
    }

}