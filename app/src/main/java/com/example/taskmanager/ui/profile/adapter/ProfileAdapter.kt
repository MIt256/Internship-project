package com.example.taskmanager.ui.profile.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.graphics.toColorInt
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.taskmanager.R
import com.example.taskmanager.databinding.ProfileRecyclerItemBinding
import com.example.taskmanager.ui.entities.ProfileStatisticItem

class ProfileAdapter : ListAdapter<ProfileStatisticItem, ProfileAdapter.ProfileViewHolder>(ProfileItemDiffCallback()) {

    inner class ProfileViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = ProfileRecyclerItemBinding.bind(view)
        fun bind(item: ProfileStatisticItem) = with(binding) {
            title.text = item.type
            count.text = "${item.count} Tasks"
            card.setCardBackgroundColor(item.color.toColorInt())
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProfileViewHolder {
        return ProfileViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.profile_recycler_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ProfileViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ProfileItemDiffCallback : DiffUtil.ItemCallback<ProfileStatisticItem>() {
        override fun areItemsTheSame(oldItem: ProfileStatisticItem, newItem: ProfileStatisticItem): Boolean = oldItem == newItem

        override fun areContentsTheSame(oldItem: ProfileStatisticItem, newItem: ProfileStatisticItem): Boolean = oldItem == newItem

    }
}