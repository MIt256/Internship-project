package com.example.taskmanager.ui.taskinfo.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.taskmanager.data.repository.TaskRepository
import com.example.taskmanager.ui.task.entities.Task
import com.example.taskmanager.ui.task.entities.User
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.catch
import javax.inject.Inject

@HiltViewModel
class TaskInfoViewModel  @Inject constructor(private val repository: TaskRepository) : ViewModel() {

    val currentException = MutableSharedFlow<String>()

    private var _currentTask = MutableLiveData<Task>()
    var currentTask: LiveData<Task> = _currentTask

    private val _taskMemberList = MutableLiveData<MutableList<User>>()
    val taskMemberList: LiveData<MutableList<User>> = _taskMemberList

    fun setMemberSearch(taskId: String) {
        currentTask = repository.getTaskById(taskId).catch { it.message?.let { currentException.tryEmit(it) } }.asLiveData()
        //todo add members
    }
}