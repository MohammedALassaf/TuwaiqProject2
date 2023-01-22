package com.example.tuwaiqproject2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class TaskRecyclerView(
    private val tasks: List<Tasks>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    inner class StringViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val name = view.findViewById<TextView>(R.id.taskdesc)
        val checkBox = view.findViewById<TextView>(R.id.checkBox)
        val vh = RecyclerVH(view)
        fun bind(task: Tasks) {
            name.text = task.task
            checkBox.text = task.complete
            vh.bindData(task)

        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val context = parent.context
        val inflater =
            LayoutInflater.from(context).inflate(R.layout.task_item_layout, parent, false)
        return StringViewHolder(inflater)
    }

    override fun getItemCount(): Int = tasks.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val task = tasks[position]
        if (holder is StringViewHolder) holder.bind(task)
    }


}