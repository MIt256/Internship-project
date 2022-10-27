package com.example.taskmanager.ui.newTask

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.taskmanager.R
import com.example.taskmanager.databinding.MemberIconListItemBinding
import com.example.taskmanager.databinding.MemberListItemBinding
import com.example.taskmanager.ui.task.entities.TaskMember

class UserIconAdapter () : RecyclerView.Adapter<UserIconAdapter.UserIconViewHolder>() {

    private var users = listOf<TaskMember>()
    private var onItemClickListener: ((Int) -> Unit)? = null
    fun setOnItemClickListener(listener: (Int) -> Unit) {
        onItemClickListener = listener
    }

    inner class UserIconViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = MemberIconListItemBinding.bind(view)
        fun bind(memberItem: TaskMember) {
            Glide.with(binding.root)
                .load(memberItem.avatarUrl)
                .error(R.drawable.ic_no_image)
                .into(binding.profileImage)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserIconViewHolder {
        return UserIconViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.member_icon_list_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: UserIconViewHolder, position: Int) {
        holder.itemView.setOnClickListener {
            onItemClickListener?.let {
                it(position)
            }
        }
        holder.bind(users[position])
    }

    override fun getItemCount(): Int {
        return users.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setUsers(memberList: List<TaskMember>) {
        users = memberList.toMutableList()
        notifyDataSetChanged()
    }
}