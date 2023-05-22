package com.example.taskmanager.ui.newquick.vm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.taskmanager.data.repository.QuickNotesRepository
import com.example.taskmanager.utils.DatePatterns
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

@HiltViewModel
class NewQuickViewModel @Inject constructor(private val repository: QuickNotesRepository) : ViewModel() {

    val currentException = MutableSharedFlow<String>(extraBufferCapacity = 1)
    val successMessage = MutableSharedFlow<String>(extraBufferCapacity = 1)

    var description = MutableLiveData<String>()
    var color = MutableLiveData<String>()
    var title = MutableLiveData<String>()
    var sd = MutableLiveData<String>()
    var st = MutableLiveData<String>()
    var ed = MutableLiveData<String>()
    var et = MutableLiveData<String>()

    fun setDate(date: Date) {
        if (sd.value.isNullOrEmpty()) {
            sd.value = SimpleDateFormat(DatePatterns.SIMPLE_DATE).format(date)
        }
        else
            ed.value = SimpleDateFormat(DatePatterns.SIMPLE_DATE).format(date)
    }

    fun setTime(date: String) {
        if (st.value.isNullOrEmpty())
            st.value = date
        else
            et.value = date
    }

    fun createNewQuickNote() {
        if (quickNoteCheck())
            viewModelScope.launch {
                try {
                    repository.addNewQuick(
                        title.value ?: throw Exception("Title is null"),
                        description.value ?: throw Exception("Title is null"),
                        color.value ?: throw Exception("Color is null"),
                        sd.value ?: throw Exception("Color is null"),
                        st.value ?: throw Exception("Color is null"),
                        ed.value ?: throw Exception("Color is null"),
                        et.value ?: throw Exception("Color is null")

                    )
                    successMessage.emit("Success, quick note was successfully added")
                } catch (exception: Exception) {
                    exception.message?.let { currentException.emit(it) }
                }
            }
        else currentException.tryEmit("Choose color, enter length of the title greater than 3")
    }

    private fun quickNoteCheck() = ((description.value?.length ?: 0 > 2) && color.value != null)

}