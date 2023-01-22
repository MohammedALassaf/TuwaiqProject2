package com.example.tuwaiqproject2

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import androidx.appcompat.widget.Toolbar
import com.google.android.gms.tasks.Task

class AddActivity : AppCompatActivity() {
    var database = TasksDatabase(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)
        supportActionBar?.hide()
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

            var task = if (check) {
                Tasks(name, "Finished")
            } else {
                Tasks(name, "Not Finished")
            }
            database.insertTask(task)
            finish()
        }
    }
}