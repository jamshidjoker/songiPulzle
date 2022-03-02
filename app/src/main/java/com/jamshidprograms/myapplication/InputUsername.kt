package com.jamshidprograms.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import com.jamshidprograms.myapplication.R

class InputUsername : AppCompatActivity() {
    private lateinit var userName:EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_input_username)

        findViewById<AppCompatButton>(R.id.okbtn).setOnClickListener {
            userName = findViewById(R.id.userName)
            val intent = Intent(this@InputUsername, GameActivity::class.java)
            intent.putExtra("USERNAME",userName.toString())
            println(userName.toString())
            startActivity(intent)
        }
    }
}