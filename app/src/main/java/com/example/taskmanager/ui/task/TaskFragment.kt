package com.example.taskmanager.ui.task

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.taskmanager.R
import com.example.taskmanager.UserSharedViewModel
import com.example.taskmanager.databinding.FragmentTaskBinding
import com.example.taskmanager.ui.task.adapter.TasksAdapter
import com.example.taskmanager.ui.task.entities.Task
import com.example.taskmanager.ui.task.vm.TaskViewModel
import com.google.android.material.tabs.TabLayout
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import java.util.*
import kotlin.time.Duration.Companion.days

@AndroidEntryPoint
class TaskFragment : Fragment() {

    private lateinit var binding: FragmentTaskBinding
    private val viewModel: TaskViewModel by viewModels()
    private val sharedViewModel: UserSharedViewModel by activityViewModels()
    val tasksAdapter = TasksAdapter()

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

        sharedViewModel.workManagerStart()

        binding.filter.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                viewModel.tasks.value?.let { setTasks(it) }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }
        })

        binding.taskList.adapter = tasksAdapter
        tasksAdapter.setOnItemClickListener {
            //todo event click
            val bundle = Bundle()
            bundle.putString("id", it)
            findNavController().navigate(R.id.action_navigation_task_to_taskInfoFragment,bundle)
        }

        viewModel.tasks.observe(this.viewLifecycleOwner) {
            setTasks(it)
        }

        viewModel.currentException.onEach {
            Toast.makeText(
                context,
                "Error: $it",
                Toast.LENGTH_SHORT
            ).show()
        }.launchIn(viewLifecycleOwner.lifecycleScope)

    }

    private fun setTasks(tasks:List<Task>){
        tasksAdapter.setTasks(
            when(binding.filter.selectedTabPosition){
                0 -> tasks.filter{ it.dueDate.dayOfMonth == Calendar.getInstance().get(Calendar.DAY_OF_MONTH)}
                1 -> tasks.filter{ it.dueDate.month.value == Calendar.getInstance().get(Calendar.MONTH)+1}
                else -> {tasks}
            }
        )

    }

}