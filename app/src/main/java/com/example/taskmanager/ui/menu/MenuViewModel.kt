package com.example.taskmanager.ui.menu

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.taskmanager.ui.newTask.entities.Project
import com.example.taskmanager.ui.task.entities.User
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.buffer
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MenuViewModel @Inject constructor(private val repository: ProjectsRepository) : ViewModel() {

    var currentException = MutableSharedFlow<String>()

    private var _projects = MutableLiveData<List<Project>>()
    val projects: LiveData<List<Project>> = _projects

    private fun setException(exception: String) =
        viewModelScope.launch { currentException.emit(exception) }


    fun getProjectsFromDB(){
        viewModelScope.launch {
            try {
                repository.getProjects().collect{
                    _projects.value = it
                }
            } catch (exception: Exception) {
                exception.message?.let { setException(it) }
            }
        }
    }
}