package com.jamshidprograms.myapplication

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.RelativeLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import com.jamshidprograms.myapplication.R
import com.jamshidprograms.myapplication.databinding.ActivityGameBinding
import java.util.*
import kotlin.collections.ArrayList
import kotlin.concurrent.schedule
import kotlin.math.absoluteValue
import com.jamshidprograms.myapplication.InputUsername as InputUsername

class GameActivity : AppCompatActivity() {
    lateinit var binding: ActivityGameBinding
    var allButtons = ArrayList<ArrayList<AppCompatButton>>()
    var numbers = ArrayList<Int>()
    private var scoreCounter = 0
    var timeCounter = 0
    lateinit var score: TextView
    lateinit var time: TextView
    lateinit var passiveCoordinate: Coordinate
    lateinit var btnParent: RelativeLayout
    lateinit var timer: Timer
    lateinit var menu:AppCompatButton
    lateinit var restart1: AppCompatButton
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGameBinding.inflate(layoutInflater)
        setContentView(binding.root)
        restart1 = findViewById(R.id.restartbtn)
        btnParent = findViewById(R.id.btnParent)
        menu = findViewById(R.id.menu)
        menu.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }
        loadAllViews()
        loadNumbers()
//        shuffle()
        loadDataToView()
        setTimer()
        restart1.setOnClickListener {
            restart()
        }
    }


    fun restart() {
//        shuffle()
        loadDataToView()
        timer.cancel()
        setTimer()
    }

    fun loadAllViews() {
        var list = ArrayList<AppCompatButton>()
        for (i in 0 until btnParent.childCount) {
            val b = btnParent.getChildAt(i)
            b.setOnClickListener {
                check(it as AppCompatButton)
            }
            b.tag = Coordinate(i / 4, i % 4)
            list.add(b as AppCompatButton)
            if ((i + 1) % 4 == 0) {
                print("ssss list ${list}")
                allButtons.add(list)
                list = ArrayList()
                print("ssss matrix $allButtons")
            }
        }
        /////
        passiveCoordinate = Coordinate(3, 3)
        //////
        score = findViewById(R.id.score)
        time = findViewById(R.id.timeactgame)
    }

    fun loadNumbers() {
        for (i in 1..15) {
            numbers.add(i)
        }
    }

    fun loadDataToView() {
        var t = 0
        for (i in 0 until 4) {
            for (j in 0 until 4) {
                if (i == 3 && j == 3) {
                    allButtons[i][j].text = ""
                    allButtons[i][j].setBackgroundResource(R.drawable.bg_passive_btn)
                } else {
                    allButtons[i][j].text = "${numbers[t++]}"
                    allButtons[i][j].setBackgroundResource(R.drawable.background16)
                }
            }
        }
        scoreCounter = 0
        timeCounter = 0
        score.text = "$scoreCounter"
        time.text = "${timeFormat(timeCounter)}"
    }

    fun shuffle() {
        numbers.shuffle()
    }

    fun setTimer() {
        timer = Timer()
        timer.scheduleAtFixedRate(object : TimerTask() {
            override fun run() {
                runOnUiThread {
                    time.text = "${timeFormat(++timeCounter)}"
                }
            }
        }, 1000, 1000)
    }

    fun timeFormat(time: Int): String {
        val minute = time / 60
        val second = time % 60
        val secondFormat = if (second < 10) "0${second}" else "$second"
        val minuteFormat = if (minute < 10) "0${minute}" else "$minute"
        return "${minuteFormat}:${secondFormat}"
    }

    fun check(activeBtn: AppCompatButton) {
        val activeCoordinate = activeBtn.tag as Coordinate
        if (
            (activeCoordinate.x - passiveCoordinate.x).absoluteValue
            + (activeCoordinate.y - passiveCoordinate.y).absoluteValue == 1
        ) {
            val passiveBtn = allButtons[passiveCoordinate.x][passiveCoordinate.y]
            passiveBtn.text = activeBtn.text
            activeBtn.text = ""
            passiveBtn.setBackgroundResource(R.drawable.background16)
            activeBtn.setBackgroundResource(R.drawable.bg_passive_btn)
            passiveCoordinate.x = activeCoordinate.x
            passiveCoordinate.y = activeCoordinate.y
            ////////////////////////////////////////
            score.text = "${++scoreCounter}"
            ///////////
            if (isWin()) {
                Toast.makeText(this, "You won ! ! !", Toast.LENGTH_SHORT).show()
                timer.cancel()

                Timer("SettingUp", false).schedule(500) {
                    val intent = Intent(this@GameActivity, WinningActivity::class.java)
                    startActivity(intent)
                }
                val saveScore = binding.score.text.toString()
                val saveTime = binding.timeactgame.text.toString()
                val sharedPreferences:SharedPreferences = getSharedPreferences("dataStorage", Context.MODE_PRIVATE)
                val editor:SharedPreferences.Editor = sharedPreferences.edit()
                editor.putString("score", saveScore)
                editor.putString("time", saveTime)
                editor.apply()
            }
        }
    }

    fun isWin(): Boolean {
        if (passiveCoordinate.x != 3 && passiveCoordinate.y != 3) return false
        var isTrue = true
        for (i in 0..14) {
            isTrue = isTrue && "${i + 1}" == allButtons[(i) / 4][(i) % 4].text.toString()
        }
        return isTrue
    }

}