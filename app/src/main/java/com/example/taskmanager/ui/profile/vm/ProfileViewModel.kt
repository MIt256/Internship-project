package com.example.taskmanager.ui.profile.vm

import androidx.lifecycle.*
import com.example.taskmanager.data.repository.ProfileRepository
import com.example.taskmanager.data.repository.ProjectsRepository
import com.example.taskmanager.ui.entities.ProfileStatisticItem
import com.example.taskmanager.ui.entities.Project
import com.example.taskmanager.ui.entities.Statistic
import com.example.taskmanager.ui.task.entities.User
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(private val repository: ProfileRepository) : ViewModel() {

    val currentException = MutableSharedFlow<String>(extraBufferCapacity = 1)

    val profileInfo: LiveData<User> = repository.getProfileInfo().catch { it.message?.let { currentException.tryEmit(it) } }.asLiveData()

    val profileWorkItems: LiveData<List<ProfileStatisticItem>> = repository.getProfileWorkItems().catch { it.message?.let { currentException.tryEmit(it) } }.asLiveData()

    private var _profileStatistic= MutableLiveData<Statistic>()
    val profileStatistic: LiveData<Statistic> = _profileStatistic

    init {
        getProfileStatistic()
    }

    private fun getProfileStatistic(){
        viewModelScope.launch {
            try {
                _profileStatistic.value = repository.getProfileStatistic()
            } catch (exception: Exception) {
                exception.message?.let { currentException.emit(it) }
            }
        }
    }
}