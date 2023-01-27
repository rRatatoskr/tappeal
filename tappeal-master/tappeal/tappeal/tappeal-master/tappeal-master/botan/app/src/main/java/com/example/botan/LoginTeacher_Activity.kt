package com.example.botan

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageButton
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.POST

data class LoginRequest_T(
    val username: String,
    val password: String
)

data class LoginResponse_T(
    val success: Boolean,
    val message: String?
)

interface LoginService_T {
    @POST("login-T")
    fun login(@Body request: LoginRequest_T): Call<LoginResponse_T>
}

class LoginTeacher_Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_teacher)
        val retrofit = Retrofit.Builder()
            .baseUrl("http://172.25.15.105:3000")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(LoginService_T::class.java)
        val loginButton = findViewById<ImageButton>(R.id.login_button_T)

        val usernameEditText: EditText = findViewById(R.id.username_edit_text_T)
        val passwordEditText: EditText = findViewById(R.id.password_edit_text_T)

        loginButton.setOnClickListener {
            // ユーザー名とパスワードを取得する
            val username = usernameEditText.text.toString()
            val password = passwordEditText.text.toString()

            //ID保存

            // ログインリクエストを送信する
            val request = LoginRequest_T(username, password)
            val call = service.login(request)
            call.enqueue(object: Callback<LoginResponse_T> {
                override fun onResponse(call: Call<LoginResponse_T>, response: Response<LoginResponse_T>) {
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
                            val intent = Intent(this@LoginTeacher_Activity,TeacherMainActivity::class.java)
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

                override fun onFailure(call: Call<LoginResponse_T>, t: Throwable) {
                    // エラーを処理する
                    println("Error: $t")

                }
            })
        }

    }
}