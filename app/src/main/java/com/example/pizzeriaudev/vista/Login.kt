package com.example.pizzeriaudev.vista

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.pizzeriaudev.R

class Login : AppCompatActivity() {

    lateinit var btn_login: Button

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        btn_login = findViewById(R.id.btn_login)

        btn_login.setOnClickListener {
            val intent = Intent (this, MenuPizzas::class.java)
            startActivity(intent)
        }
    }
}