package com.keepcoding.dragonball

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //println("hola")
        //Misma manera de hacer un LOG
        //Log.v("MainActivity", "onCreateFinished")
        Log.v(MainActivity::javaClass.name, "onCreateFinished")
    }
}