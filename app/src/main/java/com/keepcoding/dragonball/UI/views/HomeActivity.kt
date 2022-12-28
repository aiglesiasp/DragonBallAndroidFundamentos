package com.keepcoding.dragonball

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.keepcoding.dragonball.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //TODO: Obtener el token que me pasan del otro activity


        //TODO: Navegar al fragment que toque
    }
}