package com.jamshidprograms.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.AppCompatButton
import com.jamshidprograms.myapplication.R
import kotlin.system.exitProcess

class MainActivity : AppCompatActivity() {
    private lateinit var newGameBtn :AppCompatButton
    private lateinit var settingsBtn :AppCompatButton
    private lateinit var aboutBtn :AppCompatButton
    private lateinit var exitBtn :AppCompatButton
    private lateinit var result :AppCompatButton
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        newGameBtn = findViewById(R.id.newGame)
        settingsBtn = findViewById(R.id.settings)
        aboutBtn = findViewById(R.id.about)
        exitBtn = findViewById(R.id.exit)
        result = findViewById(R.id.result)
        result.setOnClickListener {
            startActivity(Intent(this, ResultActivity::class.java))
        }
        exitBtn.setOnClickListener {
            finishAndRemoveTask()
            finishAffinity()
            finish()
            exitProcess(0)
        }
        newGameBtn.setOnClickListener {
            val intent = Intent(this@MainActivity, InputUsername::class.java)
            startActivity(intent)
        }
        settingsBtn.setOnClickListener {
            val intent = Intent(this@MainActivity, SettingsActivity::class.java)
            startActivity(intent)
        }
        aboutBtn.setOnClickListener {
            val intent = Intent(this, AboutActivity::class.java)
            startActivity(intent)
        }
    }
}