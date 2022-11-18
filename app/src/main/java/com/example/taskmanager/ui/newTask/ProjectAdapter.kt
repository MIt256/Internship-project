package com.example.taskmanager.ui.newTask

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.taskmanager.R
import com.example.taskmanager.databinding.ProjectListItemBinding
import com.example.taskmanager.ui.newTask.entities.Project

class ProjectAdapter() : ListAdapter<Project, ProjectAdapter.ProjectViewHolder>(ProjectItemDiffCallback()) {

    private var onItemClickListener: ((Int) -> Unit)? = null
    fun setOnItemClickListener(listener: (Int) -> Unit) {
        onItemClickListener = listener
    }

    inner class ProjectViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = ProjectListItemBinding.bind(view)
        fun bind(item: Project) = with(binding) {
            binding.projectTitle.text = item.title
            binding.projectItem.setBackgroundColor(Color.parseColor(item.color))
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProjectViewHolder {
        return ProjectViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.project_list_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ProjectViewHolder, position: Int) {
        holder.itemView.setOnClickListener {
            onItemClickListener?.let {
                it(position)
            }
        }
        holder.bind(getItem(position))
    }

    class ProjectItemDiffCallback : DiffUtil.ItemCallback<Project>() {
        override fun areItemsTheSame(oldItem: Project, newItem: Project): Boolean = oldItem == newItem

        override fun areContentsTheSame(oldItem: Project, newItem: Project): Boolean = oldItem == newItem

    }
}