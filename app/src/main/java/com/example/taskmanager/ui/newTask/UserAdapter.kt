package com.example.taskmanager.ui.newTask

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.bumptech.glide.load.model.LazyHeaders
import com.example.taskmanager.R
import com.example.taskmanager.databinding.MemberListItemBinding
import com.example.taskmanager.databinding.TaskListItemBinding
import com.example.taskmanager.ui.task.entities.TaskMember
import java.lang.System.load
import javax.inject.Inject

class UserAdapter () : ListAdapter<TaskMember, UserAdapter.UserViewHolder>(UserItemDiffCallback()) {

    private var onItemClickListener: ((Int) -> Unit)? = null
    fun setOnItemClickListener(listener: (Int) -> Unit) {
        onItemClickListener = listener
    }

    class UserViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = MemberListItemBinding.bind(view)
        fun bind(memberItem: TaskMember) = with(binding) {
            binding.email.text = memberItem.email
            binding.memberName.text = memberItem.username

            Glide.with(binding.root)
                .load(memberItem.avatarUrl)
                .error(R.drawable.ic_no_image)
                .into(binding.profileImage)

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        return UserViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.member_list_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.itemView.setOnClickListener {
            onItemClickListener?.let {
                it(position)
            }
        }
        holder.bind(getItem(position))
    }

    class UserItemDiffCallback : DiffUtil.ItemCallback<TaskMember>() {
        override fun areItemsTheSame(oldItem: TaskMember, newItem: TaskMember): Boolean = oldItem == newItem

        override fun areContentsTheSame(oldItem: TaskMember, newItem: TaskMember): Boolean = oldItem == newItem

    }
}