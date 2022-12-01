package com.example.taskmanager.ui.menu

import androidx.lifecycle.*
import com.example.taskmanager.ui.newTask.entities.Project
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MenuViewModel @Inject constructor(private val repository: ProjectsRepository) : ViewModel() {

    var currentException = MutableSharedFlow<String>()

    val projects: LiveData<List<Project>> = repository.getProjects().catch { it.message?.let { currentException.tryEmit(it) } }.asLiveData()

    val color = MutableLiveData<String>()

    fun createNewProject(title: String) {
        val colorValue = color.value
        when {
            title.length < 3 -> currentException.tryEmit("The length of the header must be greater than 3")
            colorValue == null -> currentException.tryEmit("Choose color")
            else -> {
                viewModelScope.launch {
                    try {
                        repository.addNewProject(title, colorValue).collect {
                            currentException.tryEmit(it)
                        }
                    } catch (exception: Exception) {
                        exception.message?.let { currentException.tryEmit(it) }
                    }
                }
            }
        }
    }
}
