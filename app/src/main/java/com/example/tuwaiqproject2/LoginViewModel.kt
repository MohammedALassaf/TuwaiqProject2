package com.example.tuwaiqproject2

import android.content.Context
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.auth.User

class LoginViewModel: ViewModel() {

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




}