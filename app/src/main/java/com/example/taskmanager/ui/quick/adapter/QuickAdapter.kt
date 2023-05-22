package com.example.taskmanager.ui.quick.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.graphics.toColorInt
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.taskmanager.R
import com.example.taskmanager.databinding.QuickRecyclerItemBinding
import com.example.taskmanager.ui.entities.QuickNote

class QuickAdapter : ListAdapter<QuickNote, QuickAdapter.QuickViewHolder>(QuickItemDiffCallback()) {

    inner class QuickViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = QuickRecyclerItemBinding.bind(view)
        fun bind(item: QuickNote) = with(binding) {
            quickText.text = item.description
            title.text = item.title
            sdate.text = item.dateStart.toString()
            stime.text = item.timeStart.toString()
            edate.text = item.dateEnd.toString()
            etime.text = item.timeEnd.toString()
            statusBar.setBackgroundColor(item.color.toColorInt())
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuickViewHolder {
        return QuickViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.quick_recycler_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: QuickViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class QuickItemDiffCallback : DiffUtil.ItemCallback<QuickNote>() {
        override fun areItemsTheSame(oldItem: QuickNote, newItem: QuickNote): Boolean = oldItem == newItem

        override fun areContentsTheSame(oldItem: QuickNote, newItem: QuickNote): Boolean = oldItem == newItem

    }
}