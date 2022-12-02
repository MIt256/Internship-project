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

    val currentException = MutableSharedFlow<String>(extraBufferCapacity = 1)

    val projects: LiveData<List<Project>> = repository.getProjects().catch { it.message?.let { currentException.tryEmit(it) } }.asLiveData()

    val color = MutableLiveData<String>()
    val title = MutableLiveData<String>()

    fun createNewProject() {
        if (projectCheck())
            viewModelScope.launch {
                try {
                    repository.addNewProject(
                        title.value ?: throw Exception("Title is null"),
                        color.value ?: throw Exception("Color is null")
                    )
                } catch (exception: Exception) {
                    exception.message?.let { currentException.emit(it) }
                }
            }
        else currentException.tryEmit("Choose color, enter length of the title greater than 3")
    }

    private fun projectCheck() = ((title.value?.length ?: 0 > 2) && color.value != null)

}
