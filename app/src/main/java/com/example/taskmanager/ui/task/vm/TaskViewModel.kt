package com.example.taskmanager.ui.task.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.taskmanager.data.repository.TaskRepository
import com.example.taskmanager.ui.task.entities.Task
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.catch
import javax.inject.Inject

@HiltViewModel
class TaskViewModel @Inject constructor(private val repository: TaskRepository) : ViewModel() {

    val currentException = MutableSharedFlow<String>()

    val tasks: LiveData<List<Task>> = repository.getTasks().catch { it.message?.let { currentException.tryEmit(it) } }.asLiveData()

//    fun update(){
//        tasks =  repository.getTasks().catch { it.message?.let { currentException.tryEmit(it) } }.asLiveData()
//
//    }

}