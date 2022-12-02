package com.example.taskmanager.ui.menu.adapter

import android.content.res.ColorStateList
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.widget.ImageViewCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.taskmanager.databinding.ProjectGridAddItemBinding
import com.example.taskmanager.databinding.ProjectGridItemBinding
import com.example.taskmanager.ui.menu.adapter.ItemTypes.ITEM_TYPE_ADD_BUTTON
import com.example.taskmanager.ui.menu.adapter.ItemTypes.ITEM_TYPE_PROJECT
import com.example.taskmanager.ui.newTask.entities.Project

class ProjectGridAdapter : ListAdapter<ProjectItem, ProjectItemViewHolder<ProjectItem>>(ProjectItemDiffCallback()) {

    var onItemClick: ((Project) -> Unit)? = null
    var onItemAddClick: (() -> Unit)? = null

    inner class PlainProjectViewHolder(private val itemBinding: ProjectGridItemBinding) : ProjectItemViewHolder<ProjectItem>(itemBinding) {
        override fun bind(item: ProjectItem) {
            if (item !is ProjectItem.PlainProject) return

            ImageViewCompat.setImageTintList(itemBinding.projectColorItem, ColorStateList.valueOf(Color.parseColor(item.projectItem.color)))
            itemBinding.projectName.text = item.projectItem.title

            itemView.setOnClickListener {
                onItemClick?.invoke(item.projectItem)
            }

        }
    }

    inner class AddProjectHolder(private val itemAddBinding: ProjectGridAddItemBinding) : ProjectItemViewHolder<ProjectItem>(itemAddBinding) {
        override fun bind(item: ProjectItem) {
            itemView.setOnClickListener {
                onItemAddClick?.invoke()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)
            : ProjectItemViewHolder<ProjectItem> {
        return when (viewType) {
            ITEM_TYPE_PROJECT -> {
                PlainProjectViewHolder(
                    ProjectGridItemBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
            }

            ITEM_TYPE_ADD_BUTTON -> {
                AddProjectHolder(
                    ProjectGridAddItemBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
            }
            else -> throw IllegalStateException("Unknown view type $viewType")
        }
    }

    override fun onBindViewHolder(holder: ProjectItemViewHolder<ProjectItem>, position: Int) {
        holder.bind(getItem(position))
    }

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is ProjectItem.PlainProject -> ITEM_TYPE_PROJECT
            is ProjectItem.AddItem -> ITEM_TYPE_ADD_BUTTON
        }
    }
}

class ProjectItemDiffCallback : DiffUtil.ItemCallback<ProjectItem>() {

    override fun areItemsTheSame(
        oldItem: ProjectItem,
        newItem: ProjectItem
    ): Boolean {
        return if (oldItem is ProjectItem.PlainProject && newItem is ProjectItem.PlainProject) {
            oldItem.projectItem.id == newItem.projectItem.id
        } else {
            true
        }
    }

    override fun areContentsTheSame(
        oldItem: ProjectItem,
        newItem: ProjectItem
    ): Boolean {
        return if (oldItem is ProjectItem.PlainProject && newItem is ProjectItem.PlainProject) {
            oldItem.projectItem == newItem.projectItem
        } else {
            false
        }
    }
}
