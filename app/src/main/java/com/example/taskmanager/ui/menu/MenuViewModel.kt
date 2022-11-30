package com.example.taskmanager.ui.menu

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.taskmanager.ui.newTask.entities.Project
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.catch
import javax.inject.Inject

@HiltViewModel
class MenuViewModel @Inject constructor(private val repository: ProjectsRepository) : ViewModel() {

    var currentException = MutableSharedFlow<String>()

    val projects: LiveData<List<Project>> = repository.getProjects().catch { it.message?.let { currentException.tryEmit(it) } }.asLiveData()

}