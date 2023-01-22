package com.example.tuwaiqproject2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class HomeActivity : AppCompatActivity() {
    var database = TaskDB(this)
    private lateinit var adapter: TaskRecyclerView
    private lateinit var taskList: RecyclerView
    private lateinit var allTasks: List<Tasks>
    private lateinit var tasks: List<Tasks>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        supportActionBar?.hide()
        val number = intent.getStringExtra("number").toString()
        val viewModel = ViewModelProvider(this)[HomeViewModel::class.java]
        viewModel.initDB(database)
        val toolbar = findViewById<Toolbar>(R.id.hometoolbar)
        val addButton = findViewById<Button>(R.id.addbutton)
        taskList = findViewById(R.id.list)
        allTasks = viewModel.getTasks()
        tasks = viewModel.getTasksByNum(allTasks, number)
        adapter = TaskRecyclerView(tasks)
        taskList.adapter = adapter
        listAdapter(tasks)
        addButton.setOnClickListener {
            val intent = Intent(this, AddActivity::class.java)
            intent.putExtra("number", number)
            startActivity(intent)
        }

    }

    private fun listAdapter( tasks: List<Tasks>) {
        Log.d("Works", "hello ${tasks.joinToString(" ")}")
        adapter = TaskRecyclerView(tasks)
        taskList.adapter = adapter

    }

    override fun onResume() {
        super.onResume()
        val number = intent.getStringExtra("number").toString()
        val viewModel = ViewModelProvider(this)[HomeViewModel::class.java]
        allTasks = viewModel.getTasks()
        tasks = viewModel.getTasksByNum(allTasks, number)
        listAdapter(tasks)
    }


}