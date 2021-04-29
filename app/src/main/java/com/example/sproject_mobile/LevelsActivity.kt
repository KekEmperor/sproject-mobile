package com.example.sproject_mobile

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_levels.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class LevelsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_levels)
        val sp = getSharedPreferences("idInfo", Context.MODE_PRIVATE)

        GlobalScope.launch(Dispatchers.IO) {
            val generalAverage = getGeneralAverage(sp)
            val hourAverage = getHourAverage(sp)

            runOnUiThread {
                generalAverageTextView.text = generalAverage.toString()
            }
        }
    }
}