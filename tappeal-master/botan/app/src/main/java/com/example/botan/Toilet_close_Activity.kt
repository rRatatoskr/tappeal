package com.example.botan

import android.content.Intent
import android.widget.ImageButton
import com.example.botan.Appeal_Activity
import com.example.botan.R


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class Toilet_close_Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_toilet_close)

        var buttonclose = findViewById<ImageButton>(R.id.button_toilet_close)

        buttonclose.setOnClickListener {
            val intent = Intent(this, Appeal_Activity::class.java)
            startActivity(intent)
        }
    }
}