package com.example.taskmanager.ui.task

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.taskmanager.databinding.FragmentTaskBinding
import com.example.taskmanager.dto.NetworkResult
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class TaskFragment : Fragment() {

    private lateinit var binding: FragmentTaskBinding
    private val viewModel: TaskViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTaskBinding.inflate(inflater, container, false)

        val tasksAdapter = TasksAdapter()
        binding.taskList.adapter = tasksAdapter
        tasksAdapter.setOnItemClickListener {
            //todo event click
        }

        viewModel.tasks.observe(this.viewLifecycleOwner) {
            when (it) {
                is NetworkResult.Loading -> {
                    //todo add loading
                }

                is NetworkResult.Failure -> {
                    //todo add error
                    Toast.makeText(context, "Something was wrong(", Toast.LENGTH_SHORT).show()
                }

                is NetworkResult.Success -> {
                    //todo add success
                    tasksAdapter.setTasks(it.data)
                }
            }
        }



        return binding.root
    }

}