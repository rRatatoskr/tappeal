package com.example.botan

import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.annotation.RequiresApi
import com.example.Comp2_close_Activity
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.POST
import java.time.ZonedDateTime
import java.util.*

data class ChoiceRequest(
    val username: String,
    val choice: Int,
    val timestamp: String
)


interface MyApi {
    @POST("submit-choice")
    fun submitChoice(@Body request: ChoiceRequest): Call<ResponseBody>
}

class Comp2_Activity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_comp2)

        val retrofit = Retrofit.Builder()
            .baseUrl("http://172.25.15.105:3000")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val api = retrofit.create(MyApi::class.java)

        val submitButton = findViewById<ImageButton>(R.id.submit_button)
        val radioGroup = findViewById<RadioGroup>(R.id.radio_group)

        submitButton.setOnClickListener {
            // 選択されたラジオボタンの ID を取得する
            val selectedId = radioGroup.checkedRadioButtonId
            val selectedRadioButton = findViewById<RadioButton>(selectedId)

            // ラジオボタンのテキストを取得する
            val selectedText = selectedRadioButton.text

            // 選択された選択肢を数値に変換する
            val selectedChoice = when (selectedText) {
                "分かった" -> 1
                "大体分かった" -> 2
                "少しわからなかった" -> 3
                "分からなかった" -> 4
                else -> 0 // 選択されていない場合は 0 を送信する
            }

            // SharedPreferences からユーザー名を取得する
            val sharedPreferences = getSharedPreferences("prefs", Context.MODE_PRIVATE)
            val username = sharedPreferences.getString("username", "") ?: ""

            val currentTime = Calendar.getInstance().time.toString()
            // サーバーに送信するリクエストを作成する
            val request = ChoiceRequest(username, selectedChoice, currentTime)

            val call = api.submitChoice(request)
            call.enqueue(object : Callback<ResponseBody> {
                override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                    if (response.isSuccessful) {
                        // サーバーからのレスポンスが成功した場合の処理
                        println("Success: ${response.body()}")
                        val intent = Intent(this@Comp2_Activity, Comp2_close2_Activity::class.java)
                        startActivity(intent)
                    } else {
                        // サーバーからのレスポンスが失敗した場合の処理
                        println("Error: ${response.errorBody()}")
                    }
                }

                override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                    // 通信に失敗した場合の処理
                    println("Failure: $t")
                }
            })
        }

        var buttonback = findViewById<ImageButton>(R.id.button_back_comp2)

        buttonback.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}