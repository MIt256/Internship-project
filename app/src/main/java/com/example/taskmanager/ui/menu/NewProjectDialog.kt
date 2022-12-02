package com.example.taskmanager.ui.menu

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.example.taskmanager.R
import com.example.taskmanager.databinding.NewProjectDialogBinding
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach


class NewProjectDialog : DialogFragment() {

    private lateinit var binding: NewProjectDialogBinding
    private val viewModel: MenuViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = NewProjectDialogBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("ResourceType")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        binding.colorRadioGroup.setOnCheckedChangeListener { radioGroup, i ->
            when (i) {
                R.id.color1 -> viewModel.color.value = getColor(R.color.project_color_1)
                R.id.color2 -> viewModel.color.value = getColor(R.color.project_color_2)
                R.id.color3 -> viewModel.color.value = getColor(R.color.project_color_3)
                R.id.color4 -> viewModel.color.value = getColor(R.color.project_color_4)
                R.id.color5 -> viewModel.color.value = getColor(R.color.project_color_5)
            }
        }

        binding.projectTitle.doAfterTextChanged {
            if (it.toString().length > 2)
                viewModel.title.value = it.toString()
        }

        viewModel.currentException.onEach {
            if (it == "Success")
                dialog?.dismiss()
            Toast.makeText(
                context,
                it,
                Toast.LENGTH_SHORT
            ).show()
        }.launchIn(viewLifecycleOwner.lifecycleScope)

        binding.buttonAddProject.setOnClickListener {
            viewModel.createNewProject()
        }
        super.onViewCreated(view, savedInstanceState)
    }

    private fun getColor(colorResource: Int) = String.format("#%06x", ContextCompat.getColor(binding.root.context, colorResource) and 0xffffff)

}
