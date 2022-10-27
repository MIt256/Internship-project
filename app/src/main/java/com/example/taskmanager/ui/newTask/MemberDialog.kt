package com.example.taskmanager.ui.newTask

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.taskmanager.databinding.MemberDialogBinding
import com.example.taskmanager.dto.NetworkResult


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

        Log.e("e",viewModel.toString())

        val userAdapter = UserAdapter()
        binding.recycler.adapter = userAdapter

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
                    userAdapter.setUsers(it.data)
                }
            }
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