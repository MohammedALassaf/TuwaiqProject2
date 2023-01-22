package com.example.tuwaiqproject2

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.ViewModelProvider

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class LoginActivity : AppCompatActivity() {
    var database = UserDatabase(this)
    private lateinit var users: List<Users>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
//        users = database.getUsers()
        val viewModel = ViewModelProvider(this)[LoginViewModel::class.java]
        viewModel.initDB(database)
        users = viewModel.getUsers()
        supportActionBar?.hide()
        val toolbar = findViewById<Toolbar>(R.id.logintoolbar)
        val textView = findViewById<TextView>(R.id.loginnumber)
        val login = findViewById<Button>(R.id.loginbutton)
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        for (i in users)
            Log.d("Works", "g: ${i}")
        toolbar.setNavigationOnClickListener {
            imm.hideSoftInputFromWindow(it.windowToken, 0)
            finish()
        }
        textView.setOnClickListener {
            imm.hideSoftInputFromWindow(it.windowToken, 0)
        }
        login.setOnClickListener {
            imm.hideSoftInputFromWindow(it.windowToken, 0)
            val num = textView.text.toString()
            val check = viewModel.checkUser(users, num)

            if (check) {
                val intent =Intent(this, HomeActivity::class.java)
                intent.putExtra("number", num)
                startActivity(intent)
            }else Toast.makeText(this, "Phone is not registered", Toast.LENGTH_SHORT).show()

        }
    }
}

