package com.keepcoding.dragonball

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.w(MainActivity::javaClass.name, "onCreate STARTING...")
        setContentView(R.layout.login_constraint)

        //println("hola")
        //Misma manera de hacer un LOG
        //Log.v("MainActivity", "onCreateFinished")
        Log.d(MainActivity::javaClass.name, "onCreate FINISHED...")
    }
}