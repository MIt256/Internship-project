package com.example.taskmanager.ui.newquick

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.taskmanager.R
import com.example.taskmanager.databinding.NewQuickFragmentBinding
import com.example.taskmanager.ui.newquick.vm.NewQuickViewModel
import com.example.taskmanager.ui.newtask.DatePickerFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class NewQuickFragment : Fragment() {

    private lateinit var binding: NewQuickFragmentBinding
    private val viewModel: NewQuickViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = NewQuickFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.colorRadioGroup.setOnCheckedChangeListener { radioGroup, i ->
            when (i) {
                R.id.color1 -> viewModel.color.value = getColor(R.color.project_color_1)
                R.id.color2 -> viewModel.color.value = getColor(R.color.project_color_2)
                R.id.color3 -> viewModel.color.value = getColor(R.color.project_color_4)
                R.id.color4 -> viewModel.color.value = getColor(R.color.project_color_3)
                R.id.color5 -> viewModel.color.value = getColor(R.color.project_color_5)
            }
        }

        binding.descriptionEditText.doAfterTextChanged {
            if (it.toString().length > 2)
                viewModel.description.value = it.toString()
        }

        viewModel.successMessage.onEach {
            Toast.makeText(
                context,
                it,
                Toast.LENGTH_SHORT
            ).show()
            findNavController().popBackStack()
        }.launchIn(viewLifecycleOwner.lifecycleScope)

        viewModel.currentException.onEach {
            Toast.makeText(
                context,
                it,
                Toast.LENGTH_SHORT
            ).show()
        }.launchIn(viewLifecycleOwner.lifecycleScope)

        binding.buttonAddNote.setOnClickListener {
            viewModel.title.value = binding.titleEditText4.text.toString()
            viewModel.description.value = binding.descriptionEditText.text.toString()
            viewModel.createNewQuickNote()
        }

        binding.toolbar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }

        binding.sd.setOnClickListener { DatePickerFragmentPeriod().show(childFragmentManager, "DatePicker") }
        binding.st.setOnClickListener { TimePickerFragmentPeriod().show(childFragmentManager, "TimePicker") }
        binding.ed.setOnClickListener { DatePickerFragmentPeriod().show(childFragmentManager, "DatePicker") }
        binding.et.setOnClickListener { TimePickerFragmentPeriod().show(childFragmentManager, "TimePicker") }

        viewModel.sd.observe(this.viewLifecycleOwner) { binding.sd.text = it }
        viewModel.st.observe(this.viewLifecycleOwner) { binding.st.text = it }
        viewModel.ed.observe(this.viewLifecycleOwner) { binding.ed.text = it }
        viewModel.et.observe(this.viewLifecycleOwner) { binding.et.text = it }

    }

    private fun getColor(colorResource: Int) = String.format("#%06x", ContextCompat.getColor(binding.root.context, colorResource) and 0xffffff)
}