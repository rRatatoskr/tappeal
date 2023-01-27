package com.example.botan

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton

class TeacherMainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_teacher_main)

        val buttonCompT = findViewById<ImageButton>(R.id.button_comp_T)

        buttonCompT.setOnClickListener {
            val intent = Intent(this,Comp_T::class.java)
            startActivity(intent)
        }
    }
}