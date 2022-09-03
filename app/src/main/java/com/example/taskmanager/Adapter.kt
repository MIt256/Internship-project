package com.example.taskmanager

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class Adapter(private val words: List<String>): RecyclerView.Adapter<Adapter.PageHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PageHolder  =
        PageHolder(LayoutInflater.from(parent.context).inflate(R.layout.walkthrough_item, parent, false))

    override fun onBindViewHolder(holder: PageHolder, position: Int) {
        holder.textView.text = words[position]
    }

    override fun getItemCount(): Int = words.size

    inner class PageHolder(view: View): RecyclerView.ViewHolder(view){
        val textView: TextView = view.findViewById<TextView>(R.id.tvTitle)
    }
}

