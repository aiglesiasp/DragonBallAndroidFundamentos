package com.keepcoding.dragonball.UI.viewModels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.keepcoding.dragonball.Hero
import com.keepcoding.dragonball.LoginMainActivityViewModel
import com.keepcoding.dragonball.UI.views.battleFragment.BattleFragment
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.*
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.IOException

class HomeActivityViewModel : ViewModel() {

    val stateLiveDataHome: MutableLiveData<HeroesListState> by lazy { MutableLiveData<HeroesListState>() }
    var token: String = ""
    lateinit var heroesList: List<Hero>
    var listHeroesFighting: MutableList<Hero> = arrayListOf()


    //FUNCION OBTENER LISTA HEROES DE LA API
    fun getHeroesList() {
        if(token.isBlank()) return
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
                val responseHeroes: Array<Hero> = Gson().fromJson(responseBody, Array<Hero>::class.java)
                val mapHeroes: List<Hero> = responseHeroes.map {
                    Hero(it.id, it.name, it.photo)
                }
                setValueOnMainThread(HeroesListState.Success(mapHeroes))
                heroesList = mapHeroes
            }

        })
    }


    //FUNCION PARA PREPARAR LUCHA ENTRE 2 USUARIOS
    fun selectedHeroesForBattle(selectedHero:Hero): Boolean {
        if (selectedHero.currentLive == 0) return false
        heroesList.let {
            val listHeroesExceptedSelectedHero = it.filter { hero -> hero != selectedHero && hero.currentLive > 0 }
            val randomHero = listHeroesExceptedSelectedHero[(listHeroesExceptedSelectedHero.indices).random()]
            listHeroesFighting.add(0, randomHero)
            listHeroesFighting.add(0, selectedHero)
        }
        return true
    }

    fun fight() {
        val hero1Damage = (10..60).random()
        val hero2Damage = (10..60).random()
        listHeroesFighting[0].currentLive -= hero2Damage
        listHeroesFighting[1].currentLive -= hero1Damage
    }



    //Funcion para mandar al hilo principal
    fun setValueOnMainThread(value: HomeActivityViewModel.HeroesListState) {
        viewModelScope.launch(Dispatchers.Main) {
            stateLiveDataHome.value = value
        }
    }
    //CONTROL DE ESTADOS
    sealed class HeroesListState {
        data class Success(val heroes: List<Hero>) : HeroesListState()
        data class Error(val error: String) : HeroesListState()
        object Loading: HeroesListState()
    }
}