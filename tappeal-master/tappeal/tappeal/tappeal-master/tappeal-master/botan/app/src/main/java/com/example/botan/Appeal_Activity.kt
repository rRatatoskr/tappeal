package com.example.botan

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton

class Appeal_Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_appeal)

        val buttonback = findViewById<ImageButton>(R.id.button_back_appeal)
        val buttonToilet = findViewById<ImageButton>(R.id.button_toilet)
        val buttonQue = findViewById<ImageButton>(R.id.button_que)

        buttonback.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        buttonToilet.setOnClickListener {
            val intent = Intent(this, Toilet_Activity::class.java)
            startActivity(intent)
        }

        buttonQue.setOnClickListener {
            val intent = Intent(this, Que_Activity::class.java)
            startActivity(intent)
        }
    }
}