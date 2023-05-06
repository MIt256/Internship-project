package com.example.taskmanager.ui.taskinfo

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.taskmanager.databinding.FragmentTaskInfoBinding
import com.example.taskmanager.ui.taskinfo.vm.TaskInfoViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import java.util.*

@AndroidEntryPoint
class TaskInfoFragment : Fragment() {

    private lateinit var binding: FragmentTaskInfoBinding
    private val viewModel: TaskInfoViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTaskInfoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.toolbarTaskInfo.setNavigationOnClickListener {
            findNavController().navigate(TaskInfoFragmentDirections.actionTaskInfoFragmentToNavigationTask())
        }

        arguments?.getString("id")?.let { viewModel.setMemberSearch(it) }

        viewModel.currentTask.observe(viewLifecycleOwner){
            binding.titleEditText2.setText(it.title)
            binding.descriptionEditText2.setText(it.description)
            binding.buttonAddTask2.text = it.dueDate.toString()
            binding.projectTextInfo.text = it.projectId
        }

    }

}