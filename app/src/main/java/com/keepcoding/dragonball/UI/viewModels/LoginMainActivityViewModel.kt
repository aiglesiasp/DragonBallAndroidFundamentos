package com.keepcoding.dragonball

import android.text.Editable
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.*
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.IOException
import java.util.Base64

class LoginMainActivityViewModel: ViewModel() {

    val stateLiveDataLogin : MutableLiveData<LoginState> by lazy { MutableLiveData<LoginState>() }
    var token: String = ""

    fun login (user: String, password: String) {
        setValueOnMainThread(LoginState.Loading)
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
                setValueOnMainThread(LoginState.Error(e.message.toString()))
            }
            override fun onResponse(call: Call, response: Response) {
                if(response.code != 200) {
                    Log.e("Error", "Error getting token: ${response.code}")
                    setValueOnMainThread(LoginState.Error("${response.code}"))
                    return
                }
                Log.d("Network Call", "Success getting token")
                val responseBody = response.body?.string()
                responseBody?.let {
                    setValueOnMainThread(LoginState.Success(it))
                    token = it
                }
            }
        })
    }

    //Funcion para mandar al hilo principal
    fun setValueOnMainThread(value: LoginState) {
        viewModelScope.launch(Dispatchers.Main) {
            stateLiveDataLogin.value = value
        }
    }

    //Control de estados
    sealed class LoginState {
        data class Success(val token: String): LoginState()
        data class Error(val error: String): LoginState()
        object Loading: LoginState()
    }
}