package com.example.botan

//import Toilet_close_Activity
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.TextView

class Toilet_Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_toilet)
        var buttonyes = findViewById<ImageButton>(R.id.button_toilet_yes)
        var buttonno = findViewById<ImageButton>(R.id.button_toilet_no)

        //ID取得
        val sharedPreferences = getSharedPreferences("prefs", Context.MODE_PRIVATE)
        val username = sharedPreferences.getString("username", "")
        println(username)




        buttonyes.setOnClickListener {
            val intent = Intent(this, Toilet_close_Activity::class.java)
            startActivity(intent)
        }

        buttonno.setOnClickListener {
            val intent = Intent(this, Appeal_Activity::class.java)
            startActivity(intent)
        }

    }
}