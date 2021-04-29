package com.example.sproject_mobile

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.LinearLayout
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.json.JSONObject

class MessagesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_messages)
        val sp = getSharedPreferences("idInfo", Context.MODE_PRIVATE)

        // TODO: read messages as they render
        GlobalScope.launch(Dispatchers.IO) {
            val messages = getMessages(sp)

            for (i in 0 until messages.length()) {
                val message = JSONObject(messages[i].toString())

                val mf = MessageFragment.newInstance(
                    message.getString("dateTime"),
                    message.getString("text"),
                    message.getBoolean("isRead")
                )

                LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
                )

                supportFragmentManager.beginTransaction()
                    .add(R.id.messagesHolder, mf)
                    .commit()
            }
        }
    }
}