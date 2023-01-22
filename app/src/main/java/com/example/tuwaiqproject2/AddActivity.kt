package com.example.tuwaiqproject2

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.ViewModelProvider
import com.google.android.gms.tasks.Task

class AddActivity : AppCompatActivity() {
    var database = TaskDB(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)
        supportActionBar?.hide()
        val number = intent.getStringExtra("number").toString()
        val viewModel = ViewModelProvider(this)[AddViewModel::class.java]
        viewModel.initDB(database)
        val toolbar = findViewById<Toolbar>(R.id.addtoolbar)
        val taskdesc = findViewById<EditText>(R.id.task)
        val checkBox = findViewById<CheckBox>(R.id.checkBox2)
        val addbutton = findViewById<Button>(R.id.add)
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        toolbar.setNavigationOnClickListener {
            imm.hideSoftInputFromWindow(it.windowToken, 0)
            finish()
        }
        taskdesc.setOnClickListener {
            imm.hideSoftInputFromWindow(it.windowToken, 0)
        }
        checkBox.setOnClickListener {
            imm.hideSoftInputFromWindow(it.windowToken, 0)
        }
        addbutton.setOnClickListener {
            val name = taskdesc.text.toString()
            val check = checkBox.isChecked

            val task = if (check) {
                    Tasks(number,name, "Finished")

            } else {

                    Tasks(number, name, "Not Finished")

            }
            if (viewModel.addTask(task)) {
                finish()
            }else{
                Toast.makeText(this, "Please Change Task name", Toast.LENGTH_SHORT).show()
            }
        }
    }
}