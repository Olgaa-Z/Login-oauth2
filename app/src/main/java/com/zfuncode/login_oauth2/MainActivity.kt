package com.zfuncode.login_oauth2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.zfuncode.login_oauth2.utils.SessionManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var sessionManager: SessionManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        sessionManager = SessionManager(this)
        val token = sessionManager.fetchAccessToken()
        tvToken.text = token

//        Intent to List Product
        cardProduct.setOnClickListener{
            startActivity(Intent(this, ProductList::class.java))
        }
    }
}