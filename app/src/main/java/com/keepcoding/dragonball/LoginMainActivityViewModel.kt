package com.keepcoding.dragonball

import android.text.Editable
import android.util.Log
import androidx.lifecycle.ViewModel
import okhttp3.*
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.IOException
import java.util.Base64

class LoginMainActivityViewModel: ViewModel() {


    fun login (user: String, password: String) {

        val client = OkHttpClient()
        val url = "https://dragonball.keepcoding.education/api/auth/login"
        val stringUser = "$user:$password"
        val base64StringUser = Base64.getEncoder().encodeToString(stringUser.toByteArray())
        val body = "".toRequestBody()
        val request = Request.Builder()
            .url(url)
            .addHeader("Authorization", "Basic $base64StringUser")
            .post(body)
            .build()

        val call = client.newCall(request)

        call.enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                Log.e("Error", "Error getting token: ${e.message}")
                println("Error en el login")
            }
            override fun onResponse(call: Call, response: Response) {
                if(response.code != 200) {
                    Log.e("Error", "Error getting token: ${response.code}")
                    println("Error en el login del tipo ${response.code}")
                    return
                }
                Log.d("Network Call", "Success getting token")
                val responseBody = response.body?.string()
                println(responseBody)

            }
        })
    }
}