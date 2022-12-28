package com.keepcoding.dragonball.UI.viewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.keepcoding.dragonball.Hero
import com.keepcoding.dragonball.LoginMainActivityViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeActivityViewModel : ViewModel() {

    val stateLiveData: MutableLiveData<HeroesListState> by lazy { MutableLiveData<HeroesListState>() }
    val token: String = ""







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