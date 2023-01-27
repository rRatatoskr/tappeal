package com.example.botan

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import com.example.botan.R
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.POST

data class LoginRequest(
    val username: String,
    val password: String
)

data class LoginResponse(
    val success: Boolean,
    val message: String?
)

interface LoginService {
    @POST("login")
    fun login(@Body request: LoginRequest): Call<LoginResponse>
}

class LogintappealActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_logintappeal)



        val retrofit = Retrofit.Builder()
            .baseUrl("http://172.25.15.105:3000")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(LoginService::class.java)
        val loginButton = findViewById<ImageButton>(R.id.login_button)

        val usernameEditText: EditText = findViewById(R.id.username_edit_text)
        val passwordEditText: EditText = findViewById(R.id.password_edit_text)

        loginButton.setOnClickListener {
            // ユーザー名とパスワードを取得する
            val username = usernameEditText.text.toString()
            val password = passwordEditText.text.toString()

            //ID保存

            // ログインリクエストを送信する
            val request = LoginRequest(username, password)
            val call = service.login(request)
            call.enqueue(object: Callback<LoginResponse> {
                override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                    // レスポンスを処理する
                    if (response.isSuccessful) {
                        println("Successful response. statusCode: ${response.code()} body: ${response.body()}")
                        val loginResponse = response.body()
                        if (loginResponse?.success == true) {
                            // ログイン成功
                            println("log")
                            //ID保存
                            val sharedPreferences = getSharedPreferences("prefs", Context.MODE_PRIVATE)
                            val editor = sharedPreferences.edit()

                            editor.putString("username", username)
                            editor.apply()
                            val intent = Intent(this@LogintappealActivity,MainActivity::class.java)
                            startActivity(intent)
                            // 新しいアクティビティを開始するか、既存のアクティビティを更新する
                        } else {
                            // ログイン失敗
                            println("loginerror")
                            // エラーメッセージを表示する
                        }
                    } else {
                        // レスポンスが成功しなかった
                        println("Unsuccessful response. statusCode: ${response.code()}")
                        // エラーメッセージを表示する
                    }
                }

                override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                    // エラーを処理する
                    println("Error: $t")

                }
            })
        }

    }
}