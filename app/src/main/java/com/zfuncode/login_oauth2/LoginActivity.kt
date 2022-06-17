package com.zfuncode.login_oauth2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.zfuncode.login_oauth2.data.ApiClient
import com.zfuncode.login_oauth2.data.ApiClientDua
import com.zfuncode.login_oauth2.data.request.LoginRequest
import com.zfuncode.login_oauth2.data.response.LoginResponse
import com.zfuncode.login_oauth2.utils.SessionManager
import kotlinx.android.synthetic.main.activity_login.*
import retrofit2.Call
import retrofit2.Response

class LoginActivity : AppCompatActivity() {
    private lateinit var sessionManager: SessionManager
    private lateinit var apiClient: ApiClientDua

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        apiClient = ApiClientDua()
        sessionManager = SessionManager(this)

        btnLogin.setOnClickListener(){
            val email = etEmail.text.toString()
            val password = etPassword.text.toString()

            if (email.isEmpty() || password.isEmpty()){
                Toast.makeText(this, "Isi Email/Username terlebih dahulu",Toast.LENGTH_SHORT).show()

            }else{
                apiClient.getApiService(this).login(LoginRequest(email,password))
                    .enqueue(object : retrofit2.Callback<LoginResponse>{
                        override fun onResponse(
                            call: Call<LoginResponse>,
                            response: Response<LoginResponse>
                        ) {
                            val loginResponse = response.body()
                            if (response.isSuccessful){
                                sessionManager.saveAccessToken(loginResponse!!.accessToken)
//                                val pindah = Intent(applicationContext, MainActivity::class.java)
//                                pindah.putExtra("token", dataToken.toString())
//                                startActivity(pindah)
                                startActivity(Intent(applicationContext, MainActivity::class.java))
                            }else{
                                Toast.makeText(applicationContext, "Username dan password tidak sesuai", Toast.LENGTH_LONG).show()
                            }
                        }

                        override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                            Toast.makeText(applicationContext, "Gagal Koneksi ke Server", Toast.LENGTH_LONG).show()
                        }

                    })
            }
        }

        textRegister.setOnClickListener{
            startActivity(Intent(this, RegisterActivity::class.java))
        }

    }
}