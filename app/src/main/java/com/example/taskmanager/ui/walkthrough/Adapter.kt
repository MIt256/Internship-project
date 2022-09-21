package com.example.taskmanager.ui.walkthrough

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import com.example.taskmanager.R
import com.example.taskmanager.databinding.WalkthroughItemBinding

class Adapter(private val walkthroughs: ArrayList<WalkthroughItem>,val clickListener: () -> Unit): RecyclerView.Adapter<Adapter.PageHolder>(){

    inner class PageHolder(view: View): RecyclerView.ViewHolder(view){

        private val binding = WalkthroughItemBinding.bind(view)
        fun bind(item: WalkthroughItem) = with(binding) {
            mainTextView.text = item.mainText
            bottomTextView.text = item.bottomText
            bottomImageView.setImageResource(item.bottomImage)
            mainImageView.setImageResource(item.mainImage)

            buttonNext.setText(R.string.button_get_started_text)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PageHolder =
        PageHolder(LayoutInflater.from(parent.context).inflate(R.layout.walkthrough_item, parent, false))

    override fun onBindViewHolder(holder: PageHolder, position: Int) {
        holder.itemView.findViewById<Button>(R.id.buttonNext).setOnClickListener {
            clickListener()
        }
        holder.bind(walkthroughs[position])
    }

    override fun getItemCount(): Int = walkthroughs.size


}

