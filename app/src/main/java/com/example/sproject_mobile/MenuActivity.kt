package com.example.sproject_mobile

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import io.ktor.client.features.*
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_menu.*

class MenuActivity : AppCompatActivity() {
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        val sp = getSharedPreferences("idInfo", Context.MODE_PRIVATE)
        greeting.text = getString(R.string.welcome_menu) + " " + sp.getString("firstName", "") + " " + sp.getString("lastName", "")

        levelsButton.setOnClickListener() {
            val i = Intent(this, LevelsActivity::class.java)
            startActivity(i)
        }

        messagesButton.setOnClickListener() {
            val i = Intent(this, MessagesActivity::class.java)
            startActivity(i)
        }

        measurementButton.setOnClickListener() {
            val i = Intent(this, SenderActivity::class.java)
            startActivity(i)
        }
    }


}