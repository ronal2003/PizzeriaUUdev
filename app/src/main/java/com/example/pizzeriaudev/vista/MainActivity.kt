package com.example.pizzeriaudev.vista

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.se.omapi.Session
import android.widget.Button
import com.example.pizzeriaudev.R
import retrofit2.http.POST

class MainActivity : AppCompatActivity() {

    lateinit var btn_login: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var btnLogin = findViewById<Button>(R.id.btn_login)

        btnLogin.setOnClickListener {
           val intent = Intent (this, MenuPizzas::class.java)
            startActivity(intent)
        }

    }
}