package com.jamshidprograms.myapplication
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Adapter
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import androidx.recyclerview.widget.RecyclerView
import com.jamshidprograms.myapplication.R
import com.jamshidprograms.myapplication.databinding.ActivityResultBinding

class ResultActivity : AppCompatActivity() {
    lateinit var binding:ActivityResultBinding
    lateinit var second:String
    lateinit var count:String
//    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val sharedPreferences = getSharedPreferences("dataStorage", Context.MODE_PRIVATE)
        binding.tvText.text = sharedPreferences.getString("userName", "Username topilmadi").toString() + "\n" +
                sharedPreferences.getString("score", "Yurishlar soni aniqlanmadi").toString() + "\n" +
                sharedPreferences.getString("time", "Vaqt miqdori aniqlanmadi").toString()
        binding.homew.setOnClickListener {
            startActivity(Intent(this@ResultActivity, MainActivity::class.java))
        }

//        mResulsView = findViewById(R.id.views)

//        tv_text.text = "${intent.getStringExtra("TIMEC").toString()} +            ${intent.getStringExtra("SCOREC").toString()}"

    }
}