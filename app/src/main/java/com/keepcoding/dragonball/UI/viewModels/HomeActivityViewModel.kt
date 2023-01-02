package com.keepcoding.dragonball.UI.viewModels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.keepcoding.dragonball.Hero
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.*
import java.io.IOException

class HomeActivityViewModel : ViewModel() {

    val stateLiveDataHeroes: MutableLiveData<HeroesListState> by lazy { MutableLiveData<HeroesListState>() }
    val stateLiveDataWinners: MutableLiveData<WinnerListState> by lazy { MutableLiveData<WinnerListState>() }
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
        val hero = obtenerGanador()
        if (hero != null) {
            if(hero.id.isEmpty()) {
                setValueOnMainThread(WinnerListState.EmptyWinner(hero))
                return false
            }
            if(hero.id.isNotEmpty()) {
                setValueOnMainThread(WinnerListState.SuccessWinner(hero))
                return false
            }
            return false
        }
        heroesList.let {
            val listaSinMiHeroe = it.filter { hero -> hero != selectedHero && hero.currentLive > 0 }
            val randomHero = listaSinMiHeroe[(listaSinMiHeroe.indices).random()]
            listHeroesFighting.add(0, randomHero)
            listHeroesFighting.add(0, selectedHero)
        }
        return true
    }

    fun fight() : Boolean {
        val hero1Damage = (10..60).random()
        val hero2Damage = (10..60).random()
        listHeroesFighting[0].currentLive -= hero2Damage
        listHeroesFighting[1].currentLive -= hero1Damage
        val hero1Alive = checkNoLife(listHeroesFighting[0])
        val hero2Alive = checkNoLife(listHeroesFighting[1])
        return hero1Alive || hero2Alive
    }

    //COMPROBAR LA VIDA
    private fun checkNoLife(hero: Hero) : Boolean {
        when(hero.currentLive)
        {
            in Int.MIN_VALUE..-1 -> {
                hero.currentLive = 0
                return true
            }
             0 -> return true
        }
        return false
    }

    //COMPROBAR EL GANADOR
    private fun obtenerGanador(): Hero? {
        val supervivientes = heroesList.filter {
            it.currentLive > 0
        }
        if(supervivientes.count() == 1) return supervivientes[0]
        if(supervivientes.isEmpty()) return Hero("", "", "", 0, 0)
        return null
    }


    //Funcion para mandar al hilo principal
    fun setValueOnMainThread(value: HomeActivityViewModel.WinnerListState) {
        viewModelScope.launch(Dispatchers.Main) {
            stateLiveDataWinners.value = value
        }
    }


    //Funcion para mandar al hilo principal
    fun setValueOnMainThread(value: HomeActivityViewModel.HeroesListState) {
        viewModelScope.launch(Dispatchers.Main) {
            stateLiveDataHeroes.value = value
        }
    }
    //CONTROL DE ESTADOS
    sealed class HeroesListState {
        data class Success(val heroes: List<Hero>) : HeroesListState()
        data class Error(val error: String) : HeroesListState()
        object Loading: HeroesListState()
    }

    //CONTROL DE ESTADOS
    sealed class WinnerListState {
        data class SuccessWinner(val heroe: Hero) : WinnerListState()
        data class EmptyWinner(val heroe: Hero) : WinnerListState()
    }
}