package com.keepcoding.dragonball

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.keepcoding.dragonball.databinding.LoginConstraintBinding


class LoginMainActivity : AppCompatActivity() {

    //VARIABLE BINDING
    private lateinit var binding: LoginConstraintBinding
    // Clear variables para guardar el tiempo
    private var tiempoInicio = 0L

    companion object {
        const val  TAG_TOKEN = "TOKEN"
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        println("ENTRANDO EN EL ONCREATE")
        super.onCreate(savedInstanceState)
        binding = LoginConstraintBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }








    //PARA GUARDAR EL TIEMPO, LO USARE PARA GUARDAR TOKEN
    override fun onStart() {
        println("ENTRANDO EN EL ONSTART")
        super.onStart()
        tiempoInicio = System.currentTimeMillis()
        println("")
    }

    override fun onStop() {
        println("ENTRANDO EN EL ONSTOP")
        val tiempoFin = System.currentTimeMillis()
        val tiempoEnLaApp = tiempoFin - tiempoInicio

        val sharedPreferences = getPreferences(Context.MODE_PRIVATE)

        val tiempoAnterior = sharedPreferences.getLong(TAG_TOKEN, 0L)

        val editPreferences = sharedPreferences.edit()
        editPreferences.putLong(TAG_TOKEN, tiempoEnLaApp + tiempoAnterior)
        editPreferences.apply()
        super.onStop()
    }
}