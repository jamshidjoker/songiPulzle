package com.jamshidprograms.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import com.jamshidprograms.myapplication.R

class SplashActivity : AppCompatActivity() {
    val shared by lazy {
        SharedPreferencesHelper(this)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        startTimer()
    }
    private fun startTimer() {

        object : CountDownTimer(3000, 1000) {
            override fun onFinish() {
                startActivity(Intent(this@SplashActivity, MainActivity::class.java))
            }

            override fun onTick(millisUntilFinished: Long) {

            }

        }.start()
    }
}