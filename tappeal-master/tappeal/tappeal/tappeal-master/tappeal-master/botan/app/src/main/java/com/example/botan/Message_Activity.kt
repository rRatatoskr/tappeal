package com.example.botan

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Message
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.POST
import java.util.*

data class MessageWithUsernameAndTimestamp(
    val message: String,
    val username: String,
    val timestamp: String
)

interface MessageApi {
    @POST("messages")
    fun sendMessage(@Body message: MessageWithUsernameAndTimestamp): Call<Unit>
}

class Message_Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_message)

        var buttonback = findViewById<ImageButton>(R.id.button_back_message)

        buttonback.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        val retrofit = Retrofit.Builder()
            .baseUrl("http://172.25.15.105:3000")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val messageApi = retrofit.create(MessageApi::class.java)

        val messageInput = findViewById<EditText>(R.id.message_text)
        val sendButton = findViewById<ImageButton>(R.id.button_send_message)

        sendButton.setOnClickListener {
            val messageText = messageInput.text.toString()
            if (messageText.isNotEmpty()) {
                val sharedPreferences = getSharedPreferences("prefs", Context.MODE_PRIVATE)
                val username = sharedPreferences.getString("username", "") ?: ""
                val currentTime = Calendar.getInstance().time.toString()

                val message = MessageWithUsernameAndTimestamp(messageText, username, currentTime)
                messageApi.sendMessage(message).enqueue(object : Callback<Unit> {
                    override fun onResponse(call: Call<Unit>, response: Response<Unit>) {
                        // メッセージの送信に成功したときの処理
                        println("Success: ${response.body()}")
                        val intent = Intent(this@Message_Activity, Message_close_Activity::class.java)
                        startActivity(intent)
                    }

                    override fun onFailure(call: Call<Unit>, t: Throwable) {
                        // メッセージの送信に失敗したときの処理
                        println("Failure: $t")
                    }
                })
            }
        }
    }
}





