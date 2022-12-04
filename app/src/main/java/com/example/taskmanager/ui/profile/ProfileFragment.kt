package com.example.taskmanager.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.taskmanager.R
import com.example.taskmanager.databinding.FragmentProfileBinding
import com.example.taskmanager.ui.profile.adapter.ProfileAdapter
import com.example.taskmanager.ui.profile.vm.ProfileViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class ProfileFragment : Fragment() {

    private lateinit var binding: FragmentProfileBinding
    private val viewModel: ProfileViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        binding.workRecycler.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val profileAdapter = ProfileAdapter()
        binding.workRecycler.adapter = profileAdapter

        viewModel.profileStatistic.observe(viewLifecycleOwner){
            with(binding){
                createdTaskCount.text = it.createdTasks
                completedTaskCount.text = it.completedTasks
                statisticEventsCount.text = it.events
                statisticTodoCount.text = it.todo
                statisticQuickCount.text = it.quickNotes
            }
        }

        viewModel.profileWorkItems.observe(viewLifecycleOwner){
            profileAdapter.submitList(it)
        }

        viewModel.profileInfo.observe(viewLifecycleOwner) {
            with(binding) {
                Glide.with(binding.root)
                    .load(it.avatarUrl)
                    .error(R.drawable.ic_no_image)
                    .into(profileImage)
                userName.text = it.username
                userEmail.text = it.email
            }

        }

        viewModel.currentException.onEach {
            Toast.makeText(
                context,
                it,
                Toast.LENGTH_SHORT
            ).show()
        }.launchIn(viewLifecycleOwner.lifecycleScope)

        super.onViewCreated(view, savedInstanceState)
    }
}