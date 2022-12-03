package com.example.taskmanager.ui.newtask.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.taskmanager.R
import com.example.taskmanager.databinding.MemberListItemBinding
import com.example.taskmanager.ui.task.entities.User

class UserAdapter () : ListAdapter<User, UserAdapter.UserViewHolder>(UserItemDiffCallback()) {

    private var onItemClickListener: ((Int) -> Unit)? = null
    fun setOnItemClickListener(listener: (Int) -> Unit) {
        onItemClickListener = listener
    }

    class UserViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = MemberListItemBinding.bind(view)
        fun bind(memberItem: User) = with(binding) {
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

    class UserItemDiffCallback : DiffUtil.ItemCallback<User>() {
        override fun areItemsTheSame(oldItem: User, newItem: User): Boolean = oldItem == newItem

        override fun areContentsTheSame(oldItem: User, newItem: User): Boolean = oldItem == newItem

    }
}