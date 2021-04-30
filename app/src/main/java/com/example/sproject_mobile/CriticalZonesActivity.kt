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

class CriticalZonesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_critical_zones)
        val sp = getSharedPreferences("idInfo", Context.MODE_PRIVATE)

        GlobalScope.launch(Dispatchers.IO) {
            val zones = getCriticalZones(sp)

            for (i in 0 until zones.length()) {
                val zone = JSONObject(zones[i].toString())

                val zf = ZoneFragment.newInstance(
                    zone.getString("startTime"),
                    zone.getString("finishTime"),
                    zone.getString("type")
                )

                LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
                )

                supportFragmentManager.beginTransaction()
                    .add(R.id.zones_holder, zf)
                    .commit()
            }
        }
    }
}