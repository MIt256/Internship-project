package com.example.taskmanager.ui.newTask

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.taskmanager.databinding.NewTaskFragmentBinding
import com.example.taskmanager.dto.NetworkResult
import dagger.hilt.android.AndroidEntryPoint
import java.sql.Time

@AndroidEntryPoint
class NewTaskFragment : Fragment() {

    private lateinit var binding: NewTaskFragmentBinding
    private val viewModel: NewTaskViewModel by activityViewModels()


    @SuppressLint("ClickableViewAccessibility")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = NewTaskFragmentBinding.inflate(inflater, container, false)

        binding.recyclerView.layoutManager = LinearLayoutManager(context)
        binding.iconRecycler.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

        val userAdapter = UserAdapter()
        val projectAdapter = ProjectAdapter()
        val userIconAdapter = UserIconAdapter()

        binding.iconRecycler.adapter = userIconAdapter

        Log.e("e", viewModel.toString())

        userAdapter.setOnItemClickListener {
            viewModel.setCurrentMember(it)
            setRecyclerInVisible()
            //binding.forEditText.clearFocus()
        }

        projectAdapter.setOnItemClickListener {
            viewModel.setCurrentProject(it)
            setRecyclerInVisible()
            //binding.inEditText.clearFocus()
        }

        binding.toolbar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }

        binding.forEditText.doAfterTextChanged {
            if (it.toString().length > 2)
                viewModel.setMemberSearch(it.toString())
            if (binding.forEditText.isFocused)
            setRecyclerVisible()
        }
        viewModel.currentMember.observe(this.viewLifecycleOwner) {
            binding.forEditText.setText(it.username)
        }
        viewModel.members.observe(this.viewLifecycleOwner) {
            when (it) {
                is NetworkResult.Loading -> {
                    //todo add loading
                }

                is NetworkResult.Failure -> {
                    //todo add error
                    Toast.makeText(
                        context,
                        "Something was wrong: ${it.errorMessage}",
                        Toast.LENGTH_SHORT
                    ).show()
                }

                is NetworkResult.Success -> {
                    binding.recyclerView.adapter = userAdapter
                    userAdapter.setUsers(it.data)
                }
            }
        }

        binding.inEditText.doAfterTextChanged {
            if (it.toString().length > 2)
                viewModel.setProjectSearch(it.toString())
            if (binding.inEditText.isFocused)
            setRecyclerVisible()
        }
        viewModel.projects.observe(this.viewLifecycleOwner) {
            when (it) {
                is NetworkResult.Loading -> {
                    //todo add loading
                }

                is NetworkResult.Failure -> {
                    //todo add error
                    Toast.makeText(
                        context,
                        "Something was wrong: ${it.errorMessage}",
                        Toast.LENGTH_SHORT
                    ).show()
                }

                is NetworkResult.Success -> {
                    binding.recyclerView.adapter = projectAdapter
                    projectAdapter.setProjects(it.data)
                }
            }
        }
        viewModel.currentProject.observe(this.viewLifecycleOwner) {
            binding.inEditText.setText(it.title)
        }

        binding.imageAddUser.setOnClickListener {
            MemberDialog().show(childFragmentManager, "MemberDialog")
        }

        viewModel.taskMemberList.observe(this.viewLifecycleOwner) {
            userIconAdapter.setUsers(it)
        }

        binding.buttonTime.setOnClickListener {
            //todo add change logic
            TimePickerFragment().show(childFragmentManager,"TimePicker")
            DatePickerFragment().show(childFragmentManager,"DatePicker")
        }

        viewModel.date.observe(this.viewLifecycleOwner) {
            binding.buttonTime.text = viewModel.date.value
        }

        return binding.root
    }

    private fun setRecyclerVisible() {
        binding.goneGroupe.visibility = View.GONE
        binding.recyclerView.visibility = View.VISIBLE
    }

    private fun setRecyclerInVisible() {
        binding.goneGroupe.visibility = View.VISIBLE
        binding.recyclerView.visibility = View.GONE
    }

}
