package com.example.botan

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.POST
import java.util.*


data class NameTime(val name: String, val time: Date) {
    fun toJson(): String {
        return Gson().toJson(this)
    }
    companion object {
        fun fromJson(json: String): NameTime {
            return Gson().fromJson(json, NameTime::class.java)
        }
    }
}

// Create a service interface
interface NameTimeService {
    @POST("send-name-time")
    fun sendNameTime(@Body nameTime: NameTime): Call<String>
}

class Toilet_Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_toilet)
        var buttonyes = findViewById<ImageButton>(R.id.button_toilet_yes)
        var buttonno = findViewById<ImageButton>(R.id.button_toilet_no)


        buttonno.setOnClickListener {
            val intent = Intent(this, Appeal_Activity::class.java)
            startActivity(intent)
        }


        buttonyes.setOnClickListener {
            //ID取得
            val sharedPreferences = getSharedPreferences("prefs", Context.MODE_PRIVATE)
            val username = sharedPreferences.getString("username", "") ?: ""
            println(username)

            val gson = GsonBuilder()
                .setLenient()
                .create()

            val retrofit = Retrofit.Builder()
                .baseUrl("http://172.25.15.105:3000")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()

            // Create a service interface
            val service = retrofit.create(NameTimeService::class.java)

            // Get the current time
            val currentTime = Calendar.getInstance().time

            // Send a POST request to the server with the username and the current time
            service.sendNameTime(NameTime(username, currentTime)).enqueue(object : Callback<String>{
                override fun onResponse(call: Call<String>, response: Response<String>) {
                    // Handle the response from the server
                    if (response.isSuccessful) {
                        println("Success: ${response.body()}")
                        val intent = Intent(this@Toilet_Activity, Toilet_close_Activity::class.java)
                        startActivity(intent)
                    } else {
                        println("Error: ${response.errorBody()}")
                    }
                }

                override fun onFailure(call: Call<String>, t: Throwable) {
                    // Handle the failure
                    println("Failure: $t")
                }
            })
        }
    }


    }


