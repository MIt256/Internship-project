package com.example.taskmanager.ui.newquick

import android.app.DatePickerDialog
import android.app.Dialog
import android.os.Bundle
import android.widget.DatePicker
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.example.taskmanager.ui.newquick.vm.NewQuickViewModel
import com.example.taskmanager.ui.newtask.vm.NewTaskViewModel
import java.util.*

class DatePickerFragmentPeriod : DialogFragment(), DatePickerDialog.OnDateSetListener {

    private val viewModel: NewQuickViewModel by activityViewModels()

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)

        return DatePickerDialog(requireContext(), this, year, month, day)
    }

    override fun onDateSet(view: DatePicker, year: Int, month: Int, day: Int) {
        val c = Calendar.getInstance()
        c.set(year, month, day)
        //todo add check
        viewModel.setDate(c.time)

        dialog?.dismiss()
    }
}