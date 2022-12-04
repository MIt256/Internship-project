package com.example.taskmanager.ui.newquick.vm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.taskmanager.data.repository.QuickNotesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewQuickViewModel @Inject constructor(private val repository: QuickNotesRepository) : ViewModel() {

    val currentException = MutableSharedFlow<String>(extraBufferCapacity = 1)

    val description = MutableLiveData<String>()
    val color = MutableLiveData<String>()

    fun createNewQuickNote() {
        if (quickNoteCheck())
            viewModelScope.launch {
                try {
                    repository.addNewQuick(
                        description.value ?: throw Exception("Title is null"),
                        color.value ?: throw Exception("Color is null")
                    )
                } catch (exception: Exception) {
                    exception.message?.let { currentException.emit(it) }
                }
            }
        else currentException.tryEmit("Choose color, enter length of the title greater than 3")
    }

    private fun quickNoteCheck() = ((description.value?.length ?: 0 > 2) && color.value != null)

}