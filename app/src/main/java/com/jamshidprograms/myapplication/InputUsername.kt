package com.jamshidprograms.myapplication

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import com.jamshidprograms.myapplication.R
import com.jamshidprograms.myapplication.databinding.ActivityInputUsernameBinding

class InputUsername : AppCompatActivity() {
    private lateinit var userName:EditText
    private lateinit var binding:ActivityInputUsernameBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInputUsernameBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.okbtn.setOnClickListener {
            userName = findViewById(R.id.userName)
            val intent = Intent(this@InputUsername, GameActivity::class.java)
            saveData()
            startActivity(intent)
        }
    }
    private fun saveData(){
        val userNameSave = binding.userName.text.toString()
        val sharedPreferences: SharedPreferences = getSharedPreferences("dataStorage", Context.MODE_PRIVATE)
        val editor:SharedPreferences.Editor = sharedPreferences.edit()
        editor.putString("userName", userNameSave)
        editor.apply()
    }
}