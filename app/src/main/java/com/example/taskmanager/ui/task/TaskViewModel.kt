package com.example.taskmanager.ui.task

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.taskmanager.ui.task.entities.Task
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TaskViewModel @Inject constructor(private val repository: TaskRepository) : ViewModel() {

    val currentException = MutableSharedFlow<String>()

    private val _tasks: LiveData<List<Task>> = repository.getTasks().catch { it.message?.let { setException(it) } }.asLiveData()
    val tasks: LiveData<List<Task>> = _tasks

    fun setException(exception: String) =
        viewModelScope.launch { currentException.emit(exception) }

}