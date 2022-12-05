package com.example.taskmanager.ui.walkthrough.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import com.example.taskmanager.R
import com.example.taskmanager.databinding.WalkthroughItemBinding
import com.example.taskmanager.ui.entities.WalkthroughItem

class Adapter(private val walkthroughs: ArrayList<WalkthroughItem>, val clickListener: () -> Unit) :
    RecyclerView.Adapter<Adapter.PageHolder>() {

    inner class PageHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val binding = WalkthroughItemBinding.bind(view)
        fun bind(item: WalkthroughItem) = with(binding) {
            mainText.text = item.mainText
            bottomText.text = item.bottomText
            bottomImage.setImageResource(item.bottomImage)
            mainImage.setImageResource(item.mainImage)

            buttonNext.setText(R.string.button_get_started_text)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PageHolder =
        PageHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.walkthrough_item, parent, false)
        )

    override fun onBindViewHolder(holder: PageHolder, position: Int) {
        holder.itemView.findViewById<Button>(R.id.button_next).setOnClickListener {
            clickListener()
        }
        holder.bind(walkthroughs[position])
    }

    override fun getItemCount(): Int = walkthroughs.size


}

