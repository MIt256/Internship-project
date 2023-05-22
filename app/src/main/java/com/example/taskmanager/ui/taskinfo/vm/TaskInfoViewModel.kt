package com.example.taskmanager.ui.taskinfo.vm

import androidx.lifecycle.*
import com.example.taskmanager.data.repository.TaskRepository
import com.example.taskmanager.ui.entities.Project
import com.example.taskmanager.ui.task.entities.Task
import com.example.taskmanager.ui.task.entities.User
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TaskInfoViewModel  @Inject constructor(private val repository: TaskRepository) : ViewModel() {

    val currentException = MutableSharedFlow<String>()

    private var _currentTask = MutableLiveData<Task>()
    var currentTask: LiveData<Task> = _currentTask

    private var _currentProject = MutableLiveData<String>()
    var currentProject: LiveData<String> = _currentProject

    private val _taskMemberList = MutableLiveData<MutableList<User>>()
    val taskMemberList: LiveData<MutableList<User>> = _taskMemberList

    fun setMemberSearch(taskId: String) {
        currentTask = repository.getTaskById(taskId).catch { it.message?.let { currentException.tryEmit(it) } }.asLiveData()
        //todo add members
    }
    fun getProject(id:String){
        currentProject = repository.getProjectById(id).asLiveData()
    }

    fun save(s1:String, s2:String){
        viewModelScope.launch {
            try {
                var task = currentTask.value
                if (task != null) {
                    task.title = s1
                    task.description = s2
                    repository.save(task)
                }
            } catch (exception: Exception) {
                throw exception
            }
        }











    }
}