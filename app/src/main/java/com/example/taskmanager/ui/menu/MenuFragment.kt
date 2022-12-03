package com.example.taskmanager.ui.menu

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.example.taskmanager.databinding.FragmentMenuBinding
import com.example.taskmanager.ui.menu.adapter.ProjectGridAdapter
import com.example.taskmanager.ui.menu.adapter.ProjectItem
import com.example.taskmanager.ui.menu.vm.MenuViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class MenuFragment : Fragment() {

    private lateinit var binding: FragmentMenuBinding
    private val viewModel: MenuViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMenuBinding.inflate(inflater, container, false)

        binding.projectRecycler.layoutManager = GridLayoutManager(context, 2)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val projectAdapter = ProjectGridAdapter()
        binding.projectRecycler.adapter = projectAdapter

        projectAdapter.onItemClick = { project ->
            Toast.makeText(
                context,
                "Not implemented",
                Toast.LENGTH_SHORT
            ).show()
        }

        projectAdapter.onItemAddClick = {
            NewProjectDialog().show(childFragmentManager, "ProjectDialog")
        }

        viewModel.projects.observe(viewLifecycleOwner) {
            projectAdapter.submitList(
                it.map { ProjectItem.PlainProject(it) }.plus(ProjectItem.AddItem)
            )
        }

        viewModel.currentException.onEach {
            Toast.makeText(
                context,
                it,
                Toast.LENGTH_SHORT
            ).show()
        }.launchIn(viewLifecycleOwner.lifecycleScope)

    }


}