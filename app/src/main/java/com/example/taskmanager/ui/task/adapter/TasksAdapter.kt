package com.example.taskmanager.ui.task.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.taskmanager.R
import com.example.taskmanager.databinding.TaskListItemBinding
import com.example.taskmanager.ui.task.entities.Task
import com.example.taskmanager.utils.DateTimeFormatterUtil

class TasksAdapter() : RecyclerView.Adapter<TasksAdapter.TasksViewHolder>() {

    private var tasks = mutableListOf<Task>()
    private var onItemClickListener: ((String) -> Unit)? = null
    fun setOnItemClickListener(listener: (String) -> Unit) {
        onItemClickListener = listener
    }

    inner class TasksViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = TaskListItemBinding.bind(view)
        fun bind(taskItem: Task) = with(binding) {
            //TODO add info
            if (taskItem.isCompleted) {
                statusIcon.setImageResource(R.drawable.ic_not_active)
                statusBar.setBackgroundColor(ContextCompat.getColor(binding.root.context, R.color.app_red))
            } else {
                statusIcon.setImageResource(R.drawable.ic_active_task)
                statusBar.setBackgroundColor(ContextCompat.getColor(binding.root.context, R.color.blue_button))
            }
            title.text = taskItem.title
            try{
             time.text = DateTimeFormatterUtil.simpleFormatterDate.format(taskItem.dueDate)
            } catch (ex:Exception){
                time.text = "no date"
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TasksViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.task_list_item, parent, false)
        return TasksViewHolder(view)
    }

    override fun onBindViewHolder(holder: TasksViewHolder, position: Int) {
        holder.itemView.setOnClickListener {
            //todo add click event
            onItemClickListener?.let {
                it(tasks[position].id)
            }
        }
        holder.bind(tasks[position])
    }

    override fun getItemCount(): Int {
        return tasks.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setTasks(taskList: List<Task>) {
        tasks = taskList.toMutableList()
        notifyDataSetChanged()
    }
}