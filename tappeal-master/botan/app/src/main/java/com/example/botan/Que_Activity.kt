package com.example.botan

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
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


data class NameTimeQ(val name: String, val time: Date) {
    fun toJson(): String {
        return Gson().toJson(this)
    }
    companion object {
        fun fromJson(json: String): NameTimeQ {
            return Gson().fromJson(json, NameTimeQ::class.java)
        }
    }
}

// Create a service interface
interface NameTimeQService {
    @POST("send-name-timeQ")
    fun sendNameTimeQ(@Body nameTimeQ: NameTimeQ): Call<String>
}
class Que_Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_que)
        var buttonyes = findViewById<ImageButton>(R.id.button_que_yes)
        var buttonno = findViewById<ImageButton>(R.id.button_que_no)

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
                .baseUrl("http://192.168.50.221:3000")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()

            // Create a service interface
            val service = retrofit.create(NameTimeQService::class.java)

            // Get the current time
            val currentTime = Calendar.getInstance().time

            // Send a POST request to the server with the username and the current time
            service.sendNameTimeQ(NameTimeQ(username, currentTime)).enqueue(object : Callback<String>{
                override fun onResponse(call: Call<String>, response: Response<String>) {
                    // Handle the response from the server
                    if (response.isSuccessful) {
                        println("Success: ${response.body()}")
                        val intent = Intent(this@Que_Activity, Toilet_close_Activity::class.java)
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
