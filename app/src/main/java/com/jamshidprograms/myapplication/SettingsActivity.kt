package com.jamshidprograms.myapplication

import android.content.Intent
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import android.widget.CheckBox
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import java.util.*

class SettingsActivity : AppCompatActivity() {
    private lateinit var music:CheckBox
    private lateinit var theme:CheckBox
    private lateinit var popcorn:MediaPlayer
    private lateinit var ok:AppCompatButton
    private lateinit var uz:ImageButton
    private lateinit var en:ImageButton
    private lateinit var preferes :SharedPreferencesHelper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)
        music = findViewById(R.id.musicCheck)
        theme = findViewById(R.id.themeCheck)
        ok = findViewById(R.id.okset)
        uz = findViewById(R.id.uz)
        en = findViewById(R.id.en)
        val shared by lazy {
            SharedPreferencesHelper(this)
        }

        popcorn=MediaPlayer.create(this, R.raw.popcorn)
        ok.setOnClickListener {
            if (music.isChecked){
                popcorn.start()
            }else{
                popcorn.stop()
            }
        this.uz.setOnClickListener {
            shared.setLanguage("uz", this)
            startActivity(Intent(this, MainActivity::class.java))
        }
        this.en.setOnClickListener {
            shared.setLanguage("en", this)
            startActivity(Intent(this, MainActivity::class.java))
//                changeLanguage()
            }
           theme.isChecked = shared.getNightMode()
            theme.setOnClickListener {
                shared.setNightMode(theme.isChecked)
                if (theme.isChecked){
                    preferes.setNightMode(true)
                }
            }

            val intent = Intent(this@SettingsActivity, MainActivity::class.java)
            startActivity(intent)
        }
    }

    fun changeLanguage(){
        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(applicationContext)
        val language = sharedPreferences.getString("language","bak")
        Toast.makeText(applicationContext,language,Toast.LENGTH_SHORT).show()
        if(language=="English"){
            Toast.makeText(applicationContext,"English",Toast.LENGTH_SHORT).show()
            language("en")
        }else if(language=="Uzbek"){
            Toast.makeText(applicationContext,"Turkish",Toast.LENGTH_SHORT).show()
            language("uz")
        }
    }
    private fun language(language: String){
        val locale = Locale(language)
        Locale.setDefault(locale)
        val resources = resources
        val configuration = resources.configuration
        configuration.locale= locale
        resources.updateConfiguration(configuration, resources.displayMetrics)
    }
}