package com.jamshidprograms.myapplication

import android.content.Intent

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import com.jamshidprograms.myapplication.R

class WinningActivity : AppCompatActivity() {
    private lateinit var score:TextView
    private lateinit var time:TextView
    private lateinit var login:TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_winning)
        score = findViewById(R.id.scorew)
        time = findViewById(R.id.timew)
        login = findViewById(R.id.loginw)
        score.text = "SCORECOUNTER : " + intent.getStringExtra("SCORE")
        time.text = "TIME : " + intent.getStringExtra("TIME")
        startTimer()


//        login.text = "" + intent.getStringExtra("LOGIN")
    }
    private fun startTimer() {

        object : CountDownTimer(3000, 1000) {
            override fun onFinish() {
                val intent = Intent(this@WinningActivity, ResultActivity::class.java)
                intent.putExtra("SCOREC","SCORECOUNTER : " + intent.getStringExtra("SCORE"))
                intent.putExtra("TIMEC", "TIME : " + intent.getStringExtra("TIME"))
                startActivity(intent)

            }

            override fun onTick(millisUntilFinished: Long) {

            }

        }.start()
    }
}
