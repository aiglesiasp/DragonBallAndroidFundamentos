package com.keepcoding.dragonball.UI.viewModels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.keepcoding.dragonball.Hero
import com.keepcoding.dragonball.LoginMainActivityViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.*
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.IOException

class HomeActivityViewModel : ViewModel() {

    val stateLiveData: MutableLiveData<HeroesListState> by lazy { MutableLiveData<HeroesListState>() }
    var token: String = ""




    //FUNCION OBTENER LISTA HEROES DE LA API
    fun getHeroesList() {
        setValueOnMainThread(HeroesListState.Loading)
        val client = OkHttpClient()
        val url = "https://dragonball.keepcoding.education/api/heros/all"
        val body = FormBody.Builder()
            .add("name", "")
            .build()
        val request = Request.Builder()
            .url(url)
            .addHeader("Authorization", "Bearer $token")
            .post(body)
            .build()

        val call = client.newCall(request)

        call.enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                Log.e(HomeActivityViewModel::javaClass.name, "Error")
                setValueOnMainThread(HeroesListState.Error(e.message.toString()))
            }

            override fun onResponse(call: Call, response: Response) {
                Log.d(HomeActivityViewModel::javaClass.name, "Success")
                val responseBody = response.body?.string()
                println(responseBody)
            }

        })


    }



    //Funcion para mandar al hilo principal
    fun setValueOnMainThread(value: HomeActivityViewModel.HeroesListState) {
        viewModelScope.launch(Dispatchers.Main) {
            stateLiveData.value = value
        }
    }
    //CONTROL DE ESTADOS
    sealed class HeroesListState {
        data class Success(val heroes: List<Hero>) : HeroesListState()
        data class Error(val error: String) : HeroesListState()
        object Loading: HeroesListState()
    }
}