package com.example.sproject_mobile

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_sender.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class SenderActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sender)
        val sp = getSharedPreferences("idInfo", Context.MODE_PRIVATE)

        sendButton.setOnClickListener() {
            GlobalScope.launch(Dispatchers.IO) {
                val result = sendMeasurements(sp, levelTextView.text.toString())

                runOnUiThread {
                    if (result != "") {
                        val builder = AlertDialog.Builder(this@SenderActivity)
                        builder.setTitle("Успіх")
                        builder.setMessage(result)
                        builder.setPositiveButton(
                            "OK"
                        ) { _, _ ->
                            finish()
                        }
                        builder.show()
                    } else {
                        val builder = AlertDialog.Builder(this@SenderActivity)
                        builder.setTitle("Виникла помилка")
                        builder.setMessage("Сталася невідома нам помилка. Будь ласка, спробуйте пізніше.")
                        builder.show()
                    }
                }
            }
        }
    }
}