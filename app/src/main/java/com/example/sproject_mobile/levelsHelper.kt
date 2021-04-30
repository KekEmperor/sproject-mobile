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
        val data = it.get<HttpResponse>(
            "http://10.0.2.2:30030/patient/weekAverage/" + sp.getString(
                "patientId",
                ""
            )
        ) {
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

    HttpClient().use {
        val data = it.get<HttpResponse>(
            "http://10.0.2.2:30030/patient/hourLevel/" +
                    sp.getString("patientId", "") + "/" + hour
        ) {
            header("x-auth-token", sp.getString("token", ""))
        }

        var average: String = ""
        if (data.status == HttpStatusCode.OK) {
            average = data.readText()
        }

        return average.toDouble()
    }
}

suspend fun sendMeasurements(sp: SharedPreferences, level: String): String {
    HttpClient().use {
        val data = it.post<HttpResponse>("http://10.0.2.2:30030/patient/sendMeasurements") {
            body = TextContent(
                "{\"patientId\": \"${sp.getString("patientId", "")}\"," +
                        "\"level\": ${level}}",
                ContentType.Application.Json
            )
            header("x-auth-token", sp.getString("token", ""))
        }

        if (data.status == HttpStatusCode.Created) {
            return data.readText()
        }
        return ""
    }
}

suspend fun getCriticalZones(sp: SharedPreferences): JSONArray {
    HttpClient().use {
        val data = it.get<HttpResponse>("http://10.0.2.2:30030/patient/critZones/" + sp.getString("patientId", "")) {
            header("x-auth-token", sp.getString("token", ""))
        }

        var zones = JSONArray()

        if (data.status == HttpStatusCode.OK) {
            zones = JSONArray(data.readText())
        }

        return zones
    }
}

suspend fun getLastLevels(sp: SharedPreferences): JSONArray {
    HttpClient().use {
        val data = it.get<HttpResponse>("http://10.0.2.2:30030/patient/getLastLevels/" + sp.getString("patientId", "")) {
            header("x-auth-token", sp.getString("token", ""))
        }

        var levels = JSONArray()

        Log.i("stat", data.status.toString())
        if (data.status == HttpStatusCode.OK) {
            levels = JSONArray(data.readText())
        }

        return levels
    }
}
