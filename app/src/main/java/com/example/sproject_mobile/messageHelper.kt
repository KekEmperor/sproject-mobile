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

suspend fun getMessages(sp: SharedPreferences): JSONArray {
    HttpClient().use {
        val data = it.get<HttpResponse>("http://10.0.2.2:30030/patient/getMessages/" + sp.getString("patientId", "")) {
            header("x-auth-token", sp.getString("token", ""))
        }
        var messages = JSONArray()

        if (data.status == HttpStatusCode.OK) {
            messages = JSONArray(data.readText())
        }
        return messages
    }
}