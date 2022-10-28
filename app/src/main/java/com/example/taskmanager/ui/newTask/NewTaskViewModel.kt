package com.example.taskmanager.ui.newTask

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.taskmanager.dto.NetworkResult
import com.example.taskmanager.ui.newTask.entities.NewTask
import com.example.taskmanager.ui.newTask.entities.Project
import com.example.taskmanager.ui.task.entities.TaskMember
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewTaskViewModel @Inject constructor(private val repository: NewTaskRepository) :
    ViewModel() {

    private var _taskMemberList = MutableLiveData<MutableList<TaskMember>>()
    val taskMemberList: LiveData<MutableList<TaskMember>> = _taskMemberList

    private var _members = MutableLiveData<NetworkResult<List<TaskMember>>>()
    val members: LiveData<NetworkResult<List<TaskMember>>> = _members

    private var _currentMemberSearch = MutableLiveData<String>()
    private val currentMemberSearch: LiveData<String> = _currentMemberSearch

    private var _currentMember = MutableLiveData<TaskMember>()
    val currentMember: LiveData<TaskMember> = _currentMember

    private var _projects = MutableLiveData<NetworkResult<List<Project>>>()
    val projects: LiveData<NetworkResult<List<Project>>> = _projects

    private var _currentProject = MutableLiveData<Project>()
    val currentProject: LiveData<Project> = _currentProject

    private var _currentProjectSearch = MutableLiveData<String>()
    val currentProjectSearch: LiveData<String> = _currentProjectSearch

    private var _date = MutableLiveData<String>()
    val date: LiveData<String> = _date

    init {
        _taskMemberList.value = mutableListOf()
    }

    fun setDate(date: String) {
        _date.value = date
    }

    fun addMember(memberPosition: Int) {
        val item = (members.value as NetworkResult.Success<List<TaskMember>>).data[memberPosition]
        if (_taskMemberList.value?.contains(item) != true)
            _taskMemberList.value = (_taskMemberList.value?.plus(item)
                ?: mutableListOf(item)) as MutableList<TaskMember>?
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
        _currentProject.value =
            (projects.value as NetworkResult.Success<List<Project>>).data[position]
    }

    fun setCurrentMember(position: Int) {
        _currentMember.value =
            (members.value as NetworkResult.Success<List<TaskMember>>).data[position]
    }

    private fun searchMembers() {
        viewModelScope.launch {
            repository.searchTaskMembers(currentMemberSearch.value.toString()).collect {
                //todo add try/catch for exceptions
                _members.postValue(it)
            }

        }
    }

    private fun searchProjects() {
        viewModelScope.launch {
            repository.searchProjects(currentProjectSearch.value.toString()).collect {
                //todo add try/catch for exceptions
                _projects.postValue(it)
            }

        }
    }

    fun createTask(title:String,description:String) {
        val task:NewTask
        try {
            task = NewTask(
                assignedTo = currentMember.value?.id ?: throw Exception("Member is undefined"),
                description = description,
                dueDate = date.value ?: throw Exception("Date is undefined"),
                members = taskMemberList.value ,
                ownerId = currentMember.value?.id ?: throw Exception("Member is undefined"),
                projectId = currentProject.value?.id ?: throw Exception("Project is undefined"),
                title = title
            )
            viewModelScope.launch {
                repository.createNewTask(task).collect {
                    //todo add collect?
                }
            }

        } catch(e:Exception){
            //todo show exception
        }
    }

}