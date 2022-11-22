package com.example.taskmanager.ui.menu

import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.ColorFilter
import android.graphics.PorterDuff
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.graphics.toColorInt
import androidx.core.widget.ImageViewCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.taskmanager.R
import com.example.taskmanager.databinding.ProjectGridItemBinding
import com.example.taskmanager.ui.newTask.entities.Project

class ProjectGridAdapter() : ListAdapter<Project, ProjectGridAdapter.ProjectViewHolder>(ProjectItemDiffCallback()) {

    private var onItemClickListener: ((Int) -> Unit)? = null
    fun setOnItemClickListener(listener: (Int) -> Unit) {
        onItemClickListener = listener
    }

    class ProjectViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = ProjectGridItemBinding.bind(view)
        fun bind(projectItem: Project) = with(binding) {
            ImageViewCompat.setImageTintList(binding.projectColorItem, ColorStateList.valueOf(Color.parseColor(projectItem.color)));

            binding.projectName.text = projectItem.title
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProjectViewHolder {
        return ProjectViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.project_grid_item, parent, false)
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