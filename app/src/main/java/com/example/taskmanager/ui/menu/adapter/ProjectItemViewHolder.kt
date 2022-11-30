package com.example.taskmanager.ui.menu.adapter

import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

abstract class ProjectItemViewHolder<T> constructor(
    private val binding: ViewBinding
) : RecyclerView.ViewHolder(binding.root) {
    abstract fun bind(item: T)
}