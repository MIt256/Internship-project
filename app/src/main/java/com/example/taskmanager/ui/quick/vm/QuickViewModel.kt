package com.example.taskmanager.ui.quick.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.taskmanager.data.repository.ProfileRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import javax.inject.Inject

@HiltViewModel
class QuickViewModel @Inject constructor(private val repository: QuickRepository) : ViewModel() {

    val currentException = MutableSharedFlow<String>(extraBufferCapacity = 1)

    val quicks: LiveData<List<Quick>> = repository.getProfileInfo().catch { it.message?.let { currentException.tryEmit(it) } }.asLiveData()

}