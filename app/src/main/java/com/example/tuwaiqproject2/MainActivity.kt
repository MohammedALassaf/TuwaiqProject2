package com.example.tuwaiqproject2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    val scope = CoroutineScope(Dispatchers.Main)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()


        val login = findViewById<Button>(R.id.login)
        val register = findViewById<Button>(R.id.register)

        login.setOnClickListener {


            startActivity(Intent(this , LoginActivity::class.java))
        }
        register.setOnClickListener {
            startActivity(Intent(this , RegisterActivity::class.java))
        }
    }

}