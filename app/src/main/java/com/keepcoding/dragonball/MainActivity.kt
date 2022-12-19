package com.keepcoding.dragonball

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle


class MainActivity : AppCompatActivity() {

    // Clear variables para guardar el tiempo
    private var tiempoInicio = 0L

    companion object {
        const val  TAG_TEMP = "Tiempo"
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        println("ENTRANDO EN EL ONCREATE")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_constraint)
    }

    override fun onStart() {
        println("ENTRANDO EN EL ONSTART")
        super.onStart()
        tiempoInicio = System.currentTimeMillis()
        println("")
    }

    override fun onResume() {
        println("ENTRANDO EN EL ONRESUME")
        super.onResume()
    }

    override fun onPause() {
        println("ENTRANDO EN EL ONPAUSE")
        super.onPause()
    }

    override fun onStop() {
        println("ENTRANDO EN EL ONSTOP")
        val tiempoFin = System.currentTimeMillis()
        val tiempoEnLaApp = tiempoFin - tiempoInicio

        val sharedPreferences = getPreferences(Context.MODE_PRIVATE)

        val tiempoAnterior = sharedPreferences.getLong(TAG_TEMP, 0L)

        val editPreferences = sharedPreferences.edit()
        editPreferences.putLong(TAG_TEMP, tiempoEnLaApp + tiempoAnterior)
        editPreferences.apply()
        super.onStop()
    }

    override fun onRestart() {
        println("ENTRANDO EN EL ONRESTART")
        super.onRestart()
    }

    override fun onDestroy() {
        println("ENTRANDO EN EL ONDESTROY")
        super.onDestroy()
    }
}