package com.example.tuwaiqproject2

import androidx.lifecycle.ViewModel

class AddViewModel:ViewModel() {
    private lateinit var db: TaskDB
    private lateinit var tasks: List<Tasks>

    fun initDB(database: TaskDB){
        db = database
    }

    fun getTasks(): List<Tasks>{
        return db.getTasks()
    }
    fun getTasksByNum(tasks: List<Tasks> , number: String): List<Tasks>{
        val task =  arrayListOf<Tasks>()
        for (i in tasks){
            if (i.number == number){
                task.add(i)
            }
        }
        return task
    }
    fun addTask(task: Tasks): Boolean{
        val tasks =getTasks()
        for (i in tasks){
            if (i.task == task.task){
                return false
            }
        }
        db.insertTask(task)
        return true
    }
}