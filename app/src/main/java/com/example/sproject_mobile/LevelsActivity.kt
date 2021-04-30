package com.example.sproject_mobile

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_levels.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.json.JSONObject
import java.time.LocalDateTime
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

class LevelsActivity : AppCompatActivity() {
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_levels)
        val sp = getSharedPreferences("idInfo", Context.MODE_PRIVATE)

        GlobalScope.launch(Dispatchers.IO) {
            val generalAverage = getGeneralAverage(sp)
            val hourAverage = getHourAverage(sp)

            runOnUiThread {
                generalAverageTextView.text = generalAverage.toString()
                hourAverageTextView.text = hourAverage.toString()
            }

            val levels = getLastLevels(sp)

            for (i in 0 until levels.length()) {
                val item = JSONObject(levels[i].toString())

                val ll = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
                )
                ll.setMargins(0, 10, 0, 0)

                runOnUiThread {
                    val tv = TextView(applicationContext)
                    tv.textSize = 20f
                    tv.text = ZonedDateTime.parse(item.getString("dateTime"))
                        .format(DateTimeFormatter.ofPattern(getString(R.string.date_time_formatter))) + "  -  " + item.getString("level")
                    tv.layoutParams = ll
                    levelsHolder.addView(tv)
                }
            }
        }
    }
}