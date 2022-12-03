package com.example.taskmanager.ui.newtask.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.taskmanager.data.repository.NewTaskRepository
import com.example.taskmanager.ui.entities.NewTask
import com.example.taskmanager.ui.entities.Project
import com.example.taskmanager.ui.task.entities.User
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@HiltViewModel
class NewTaskViewModel @Inject constructor(private val repository: NewTaskRepository) :
    ViewModel() {

    var currentException = MutableSharedFlow<String>(extraBufferCapacity = 1)

    private val _taskMemberList = MutableLiveData<MutableList<User>>()
    val taskMemberList: LiveData<MutableList<User>> = _taskMemberList

    private var _members = MutableLiveData<List<User>>()
    val members: LiveData<List<User>> = _members

    private var _currentMemberSearch = MutableLiveData<String>()
    private val currentMemberSearch: LiveData<String> = _currentMemberSearch

    private var _currentMember = MutableLiveData<User>()
    val currentMember: LiveData<User> = _currentMember

    private var _projects = MutableLiveData<List<Project>>()
    val projects: LiveData<List<Project>> = _projects

    private var _currentProject = MutableLiveData<Project>()
    val currentProject: LiveData<Project> = _currentProject

    private var _currentProjectSearch = MutableLiveData<String>()
    val currentProjectSearch: LiveData<String> = _currentProjectSearch

    private var _date = MutableLiveData<Date>()
    val date: LiveData<Date> = _date

    init {
        _taskMemberList.value = mutableListOf()
    }

    fun setDate(date: Date) {
        _date.value = date
    }

    fun addMember(memberPosition: Int) {
        val item = members.value?.get(memberPosition)
        if (_taskMemberList.value?.contains(item) != true)
            _taskMemberList.value = (_taskMemberList.value?.plus(item)
                ?: mutableListOf(item)) as MutableList<User>?
    }

    fun setMemberSearch(member: String) {
        _currentMemberSearch.value = member
        searchMembers()
    }

    fun setProjectSearch(project: String) {
        _currentProjectSearch.value = project
        searchProjects()
    }

    fun setCurrentProject(position: Int) {
        _currentProject.value = projects.value?.get(position)
    }

    fun setCurrentMember(position: Int) {
        _currentMember.value =
            members.value?.get(position)
    }

    private fun searchMembers() {
        viewModelScope.launch {
            try {
                repository.searchTaskMembers(currentMemberSearch.value.toString()).collect {
                    _members.value = it
                }
            } catch (exception: Exception) {
                exception.message?.let { currentException.emit(it) }
            }
        }
    }

    private fun searchProjects() {
        viewModelScope.launch {
            try {
                repository.searchProjects(currentProjectSearch.value.toString()).collect {
                    _projects.value = it
                }
            } catch (exception: Exception) {
                exception.message?.let { currentException.emit(it) }
            }
        }
    }

    fun createTask(title: String, description: String) {
        val task: NewTask
        try {
            task = NewTask(
                assignedTo = currentMember.value?.id ?: throw Exception("Member is undefined"),
                description = description,
                dueDate = date.value ?: throw Exception("Date is undefined"),
                members = taskMemberList.value,
                ownerId = currentMember.value?.id ?: throw Exception("Member is undefined"),
                projectId = currentProject.value?.id ?: throw Exception("Project is undefined"),
                title = title
            )
            viewModelScope.launch {
                try {
                    repository.createNewTask(task).collect {
                        currentException.emit(it)
                    }
                } catch (exception: Exception) {
                    throw exception
                }
            }

        } catch (e: Exception) {
            e.message?.let { currentException.tryEmit(it) }
        }
    }

}