package com.example.tuwaiqproject2

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.ViewModelProvider

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class RegisterActivity : AppCompatActivity() {
    var database = UserDatabase(this)
    private lateinit var users: List<Users>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        users = database.getUsers()
        supportActionBar?.hide()
        val toolbar = findViewById<Toolbar>(R.id.registertoolbar)
        val name = findViewById<EditText>(R.id.registername)
        val number = findViewById<EditText>(R.id.registernum)
        val email = findViewById<EditText>(R.id.registeremail)
        val register = findViewById<Button>(R.id.registerbutton)
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        toolbar.setNavigationOnClickListener {
            imm.hideSoftInputFromWindow(it.windowToken, 0)
            finish()

        }
        name.setOnClickListener {
            imm.hideSoftInputFromWindow(it.windowToken, 0)
        }
        email.setOnClickListener {
            imm.hideSoftInputFromWindow(it.windowToken, 0)
        }
        number.setOnClickListener {
            imm.hideSoftInputFromWindow(it.windowToken, 0)
        }
        register.setOnClickListener {
            imm.hideSoftInputFromWindow(it.windowToken, 0)
            val nametext = name.text.toString()
            val num = number.text.toString()
            val emailtext = email.text.toString()
            var boolean = false
            for(i in users){
                if (i.num != num){
                    boolean = false
                }else{
                    boolean = true
                    break
                }
            }
           if(!boolean) {
               val user = Users(
                   num,
                   nametext,
                   emailtext
               )
               database.insertUser(user)
               startActivity(Intent(this, HomeActivity::class.java))
           }else{
               Toast.makeText(this, "Number already registered", Toast.LENGTH_SHORT).show()
           }
        }
    }


}