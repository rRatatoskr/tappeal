package com.example.botan

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import com.example.botan.ui.login.LoginActivity

class ST_Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_st)

        val buttonT = findViewById<ImageButton>(R.id.button_t)
        val buttonS = findViewById<ImageButton>(R.id.button_s)

        buttonT.setOnClickListener {
            val intent = Intent(this, LogintappealActivity::class.java)
            startActivity(intent)
        }

        buttonS.setOnClickListener {
            val intent = Intent(this, LogintappealActivity::class.java)
            startActivity(intent)
        }
    }
}