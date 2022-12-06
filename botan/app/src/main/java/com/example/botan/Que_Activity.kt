package com.example.botan

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton

class Que_Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_que)
        var buttonyes = findViewById<ImageButton>(R.id.button_que_yes)
        var buttonno = findViewById<ImageButton>(R.id.button_que_no)

        buttonyes.setOnClickListener {
            val intent = Intent(this, Appeal_Activity::class.java)
            startActivity(intent)
        }

        buttonno.setOnClickListener {
            val intent = Intent(this, Appeal_Activity::class.java)
            startActivity(intent)
        }
    }
}