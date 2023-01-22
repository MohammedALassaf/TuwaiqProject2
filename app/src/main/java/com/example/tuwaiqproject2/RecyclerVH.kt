package com.example.tuwaiqproject2


import android.view.View
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView

class RecyclerVH(view: View) : RecyclerView.ViewHolder(view) {

    private val text = view.findViewById<TextView>(R.id.taskdesc)
    private val checkBox = view.findViewById<TextView>(R.id.checkBox)
    private val root = view.findViewById<ConstraintLayout>(R.id.root)

    fun bindData(task: Tasks) {
        text.text = task.task
        checkBox.text = task.complete


        root.setOnClickListener {
//            val context = root.context
//            val intent = Intent(context , ShowUser::class.java)
//            intent.putExtra("name" , user.name)
//
//            context.startActivity(intent)

        }
    }

}