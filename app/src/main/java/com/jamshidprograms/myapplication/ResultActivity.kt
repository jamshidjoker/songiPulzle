package com.jamshidprograms.myapplication
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Adapter
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import androidx.recyclerview.widget.RecyclerView
import com.jamshidprograms.myapplication.R

class ResultActivity : AppCompatActivity() {
    private lateinit var mResulsView: RecyclerView
    private lateinit var clear: AppCompatButton
    private lateinit var save: AppCompatButton
    private lateinit var tv_text:TextView
    lateinit var second:String
    lateinit var count:String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)
        clear = findViewById(R.id.btnclear)
        save = findViewById(R.id.button_save)
//        mResulsView = findViewById(R.id.views)
        tv_text = findViewById(R.id.tv_text)
        second = intent.getStringExtra("TIMEC").toString()
        count = intent.getStringExtra("SCOREC").toString()
        loadData()
        save.setOnClickListener {
            saveData()
            loadData()
        }
        clear.setOnClickListener {
            tv_text.clearComposingText()
        }
    }

    private fun saveData() {
        val insertedText = intent.getStringArrayExtra("TIMEC").toString() + "           ${intent.getStringExtra("SCOREC").toString()}            "
        tv_text.text = insertedText
        val sharedPreferences = getSharedPreferences("sharedPrefs", MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.apply(){
            putString("STRING_KEY", insertedText)

        }.apply()
        Toast.makeText(this, "DAta saved", Toast.LENGTH_SHORT).show()
    }

    private fun loadData() {
        val sharedPreferences = getSharedPreferences("sharedPrefs", MODE_PRIVATE)
        val savedString = sharedPreferences.getString("STRING_KEY"  , null)
        tv_text.text = savedString
    }
}