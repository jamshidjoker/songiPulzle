package com.jamshidprograms.myapplication

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import com.jamshidprograms.myapplication.R
import com.jamshidprograms.myapplication.databinding.ActivityWinningBinding

class WinningActivity : AppCompatActivity() {
    lateinit var binding:ActivityWinningBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWinningBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val sharedPreferences:SharedPreferences = getSharedPreferences("dataStorage", Context.MODE_PRIVATE)
        binding.loginw.text = sharedPreferences.getString("userName", "topilmadi")
        binding.scorew.text = sharedPreferences.getString("score", "topilmadi")
        binding.timew.text = sharedPreferences.getString("time", "topilmadi")
        startTimer()
//        login.text = "" + intent.getStringExtra("LOGIN")
    }
    private fun startTimer() {

        object : CountDownTimer(3000, 1000) {
            override fun onFinish() {
                val intent = Intent(this@WinningActivity, ResultActivity::class.java)
                startActivity(intent)
            }

            override fun onTick(millisUntilFinished: Long) {
            }

        }.start()
    }
}
