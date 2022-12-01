package com.example.taskmanager.ui.menu

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.core.view.forEach
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import com.example.taskmanager.databinding.NewProjectDialogBinding

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
            //todo
        }

        super.onViewCreated(view, savedInstanceState)
    }
}