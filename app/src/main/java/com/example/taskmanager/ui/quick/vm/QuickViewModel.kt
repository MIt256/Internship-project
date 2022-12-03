package com.example.taskmanager.ui.quick.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class QuickViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is quick Fragment"
    }
    val text: LiveData<String> = _text
}