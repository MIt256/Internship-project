package com.example.taskmanager.ui.quick

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.taskmanager.databinding.FragmentQuickBinding
import com.example.taskmanager.ui.quick.adapter.QuickAdapter
import com.example.taskmanager.ui.quick.vm.QuickViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class QuickFragment : Fragment() {

    private lateinit var binding: FragmentQuickBinding
    private val viewModel: QuickViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentQuickBinding.inflate(inflater, container, false)
        binding.quickRecycler.layoutManager = LinearLayoutManager(context)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val quickAdapter = QuickAdapter()
        binding.quickRecycler.adapter = quickAdapter

        viewModel.quickNoteNotes.observe(viewLifecycleOwner){
            quickAdapter.submitList(it)
        }

    }
}