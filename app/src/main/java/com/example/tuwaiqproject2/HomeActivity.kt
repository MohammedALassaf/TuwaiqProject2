package com.example.tuwaiqproject2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class HomeActivity : AppCompatActivity() {
    var database = TasksDatabase(this)
    private lateinit var adapter: TaskRecyclerView
    private lateinit var taskList: RecyclerView
    private var tasks = arrayListOf<Tasks>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        supportActionBar?.hide()
        val toolbar = findViewById<Toolbar>(R.id.hometoolbar)
        val addButton = findViewById<Button>(R.id.addbutton)
        taskList = findViewById(R.id.list)
        tasks = database.getTasks() as ArrayList<Tasks>
        adapter = TaskRecyclerView(tasks)
        taskList.adapter = adapter
//        listAdapter()
        addButton.setOnClickListener {
            startActivity(Intent(this, AddActivity::class.java))
        }
        toolbar.setNavigationOnClickListener {
            finish()
        }
    }

    private fun listAdapter() {
        tasks = database.getTasks() as ArrayList<Tasks>
        adapter = TaskRecyclerView(tasks)
        taskList.adapter = adapter

    }

    override fun onResume() {
        super.onResume()
        listAdapter()
    }


}