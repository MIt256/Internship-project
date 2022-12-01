package com.example.taskmanager.ui.menu

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        binding.colorRadioGroup.setOnCheckedChangeListener { radioGroup, i ->
            when (i) {
                R.id.color1 -> viewModel.color.value = "#6074F9"
                R.id.color2 -> viewModel.color.value = "#E42B6A"
                R.id.color3 -> viewModel.color.value = "#5ABB56"
                R.id.color4 -> viewModel.color.value = "#3D3A62"
                R.id.color5 -> viewModel.color.value = "#F4CA8F"
            }
        }

        viewModel.currentException.onEach {
            Toast.makeText(
                context,
                "Error: $it",
                Toast.LENGTH_SHORT
            ).show()
        }.launchIn(viewLifecycleOwner.lifecycleScope)

        binding.buttonAddProject.setOnClickListener {
            onAddButtonClick()
        }
        super.onViewCreated(view, savedInstanceState)
    }


    private fun onAddButtonClick() {
        viewModel.createNewProject(binding.projectTitle.text.toString())
    }
}
