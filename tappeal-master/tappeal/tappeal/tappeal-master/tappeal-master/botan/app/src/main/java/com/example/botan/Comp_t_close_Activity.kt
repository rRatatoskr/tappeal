package com.example.botan

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton

class Comp_t_close_Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_comp_tclose)

        var buttonclose = findViewById<ImageButton>(R.id.button_close_comp_t)

        buttonclose.setOnClickListener {
            val intent = Intent(this, TeacherMainActivity::class.java)
            startActivity(intent)
        }
    }
}