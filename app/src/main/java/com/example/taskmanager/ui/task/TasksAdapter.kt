package com.example.taskmanager.ui.task

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.taskmanager.R
import com.example.taskmanager.databinding.TaskListItemBinding
import com.example.taskmanager.ui.task.entities.Task
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TasksAdapter @Inject constructor() : RecyclerView.Adapter<TasksAdapter.TasksViewHolder>(){

    private var tasks = mutableListOf<Task>()
    private var onItemClickListener: ((Int) -> Unit)? = null
    fun setOnItemClickListener(listener: (Int) -> Unit) {
        onItemClickListener = listener
    }

    inner class TasksViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = TaskListItemBinding.bind(view)
        fun bind(taskItem: Task) = with(binding) {
            //TODO add info
            if (taskItem.isCompleted)
            statusIcon.setImageResource(R.drawable.ic_not_active)
            title.text = taskItem.title
            time.text = taskItem.dueDate
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TasksViewHolder {
        val view  = LayoutInflater.from(parent.context).inflate(R.layout.task_list_item, parent, false)
        return TasksViewHolder(view)
    }

    override fun onBindViewHolder(holder: TasksViewHolder, position: Int) {
        holder.itemView.setOnClickListener {
           //todo add click event
            onItemClickListener?.let {
                it(position)
            }
        }
        holder.bind(tasks[position])
    }

    override fun getItemCount(): Int {
        return tasks.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setTasks(taskList: List<Task>){
        tasks = taskList.toMutableList()
        notifyDataSetChanged()
    }
}