package com.example.tuwaiqproject2

import androidx.lifecycle.ViewModel

class RegisterViewModel: ViewModel() {
    private lateinit var db: UserDatabase
    private lateinit var users: List<Users>

    fun initDB(database: UserDatabase){
        db = database
    }

    fun getUsers(): List<Users>{
        return db.getUsers()
    }
    fun checkUser(users: List<Users> , number: String): Boolean{
        for (i in users){
            if (i.num ==number){
                return true
            }
        }
        return false
    }

    fun addUser(user: Users){
        db.insertUser(user)
    }




}