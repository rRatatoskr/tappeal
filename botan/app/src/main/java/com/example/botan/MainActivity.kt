package com.example.botan

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val buttonAppeal = findViewById<ImageButton>(R.id.button_appeal)
        val buttonMessage = findViewById<ImageButton>(R.id.button_message)
        val buttonGame = findViewById<ImageButton>(R.id.button_game)
        val buttonComp2 = findViewById<ImageButton>(R.id.button_comp2)

        buttonMessage.setOnClickListener{
            val intent = Intent(this,Message_Activity::class.java)
            startActivity(intent)
        }

        buttonGame.setOnClickListener{
            val intent = Intent(this,Game_Activity::class.java)
            startActivity(intent)
        }

        buttonComp2.setOnClickListener {
            val intent = Intent(this,Comp2_Activity::class.java)
            startActivity(intent)
        }
        buttonAppeal.setOnClickListener {
            val intent = Intent(this,Appeal_Activity::class.java)
            startActivity(intent)
          /*  val fragment = Fragment1()
            val transaction: FragmentTransaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.fragment1, fragment)
            transaction.addToBackStack(null);
            transaction.commit() */
        }
    }
}