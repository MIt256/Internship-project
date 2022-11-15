package com.example.taskmanager.ui.newTask

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.taskmanager.databinding.MemberDialogBinding


class MemberDialog : DialogFragment() {

    private val viewModel: NewTaskViewModel by activityViewModels()
    private lateinit var binding: MemberDialogBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = MemberDialogBinding.inflate(inflater, container, false)
        binding.recycler.layoutManager = LinearLayoutManager(context)


        val userAdapter = UserAdapter()
        binding.recycler.adapter = userAdapter

        viewModel.members.observe(this.viewLifecycleOwner) {
            userAdapter.submitList(it)
        }

        userAdapter.setOnItemClickListener {
            viewModel.addMember(it)
            dialog?.dismiss()
        }

        binding.search.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextChange(newText: String): Boolean {
                if (newText.length > 2)
                    viewModel.setMemberSearch(newText)
                return false
            }

            override fun onQueryTextSubmit(query: String): Boolean {
                return false
            }
        })

        return binding.root
    }
}