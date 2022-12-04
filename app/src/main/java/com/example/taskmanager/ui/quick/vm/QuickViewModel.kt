package com.example.taskmanager.ui.quick.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.taskmanager.data.repository.QuickNotesRepository
import com.example.taskmanager.ui.entities.Quick
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.catch
import javax.inject.Inject

@HiltViewModel
class QuickViewModel @Inject constructor(private val repository: QuickNotesRepository) : ViewModel() {

    val currentException = MutableSharedFlow<String>(extraBufferCapacity = 1)

    val quickNotes: LiveData<List<Quick>> = repository.getQuickNotes().catch { it.message?.let { currentException.tryEmit(it) } }.asLiveData()

}