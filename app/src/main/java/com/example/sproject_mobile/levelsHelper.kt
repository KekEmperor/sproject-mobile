package com.example.sproject_mobile

import android.content.SharedPreferences
import android.util.Log
import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.http.content.*
import org.json.*
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

suspend fun getGeneralAverage(sp: SharedPreferences): Double {
    HttpClient().use {
        val data = it.get<HttpResponse>("http://10.0.2.2:30030/patient/weekAverage/" + sp.getString("patientId", "")) {
            header("x-auth-token", sp.getString("token", ""))
        }
        var average = JSONObject()

        if (data.status == HttpStatusCode.OK) {
            average = JSONObject(data.readText())
        }

        return average.getDouble("weekAverageNumber")
    }
}

suspend fun getHourAverage(sp: SharedPreferences): Double {
    val now = LocalDateTime.now()
    val formatter = DateTimeFormatter.ofPattern("HH")
    val hour = now.format(formatter)

    Log.i("abobba", hour)

    /*HttpClient().use {
        val data = it.get<HttpResponse>("http://10.0.2.2:30030/patient")
    }*/

    return 42.0
}
