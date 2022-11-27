package com.example.taskmanager.ui.task

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.coroutineScope
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.taskmanager.UserSharedViewModel
import com.example.taskmanager.databinding.FragmentTaskBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

@AndroidEntryPoint
class TaskFragment : Fragment() {

    private lateinit var binding: FragmentTaskBinding
    private val viewModel: TaskViewModel by viewModels()
    private val userViewModel: UserSharedViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTaskBinding.inflate(inflater, container, false)
        binding.taskList.layoutManager = LinearLayoutManager(context)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        if (userViewModel.isAuthenticated.value == false) {
//            val action =
//                TaskFragmentDirections.actionNavigationTaskToAuthNavigationGraph()
//            findNavController().navigate(action)
//        }

        val tasksAdapter = TasksAdapter()
        binding.taskList.adapter = tasksAdapter
        tasksAdapter.setOnItemClickListener {
            //todo event click
        }

        viewModel.tasks.observe(this.viewLifecycleOwner) {
            tasksAdapter.setTasks(it)
        }

        viewModel.currentException.onEach {
            Toast.makeText(
                context,
                "Error: $it",
                Toast.LENGTH_SHORT
            ).show()
        }.launchIn(viewLifecycleOwner.lifecycleScope)


    }

}