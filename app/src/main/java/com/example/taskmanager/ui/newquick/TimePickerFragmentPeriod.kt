package com.example.taskmanager.ui.newquick

import android.app.Dialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.text.format.DateFormat.is24HourFormat
import android.widget.TimePicker
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import com.example.taskmanager.ui.newquick.vm.NewQuickViewModel
import java.util.*

class TimePickerFragmentPeriod : DialogFragment(), TimePickerDialog.OnTimeSetListener {

    private val viewModel: NewQuickViewModel by activityViewModels()

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        // Use the current time as the default values for the picker
        val c = Calendar.getInstance()
        val hour = c.get(Calendar.HOUR_OF_DAY)
        val minute = c.get(Calendar.MINUTE)

        // Create a new instance of TimePickerDialog and return it
        return TimePickerDialog(context, this, hour, minute, is24HourFormat(context))
    }

    override fun onTimeSet(view: TimePicker, hourOfDay: Int, minute: Int) {
        //todo add time logic
        viewModel.setTime("$hourOfDay:$minute")
        dialog?.dismiss()
    }
}