package com.keepcoding.dragonball

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import com.keepcoding.dragonball.databinding.LoginConstraintBinding


class LoginMainActivity : AppCompatActivity() {

    //VARIABLE BINDING
    private lateinit var binding: LoginConstraintBinding

    //VIEWMODEL
    private val viewModel: LoginMainActivityViewModel by viewModels()

    // Clear variables para guardar el tiempo
    private var tiempoInicio = 0L

    companion object {
        const val TAG_TOKEN = "TOKEN"
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        println("ENTRANDO EN EL ONCREATE")
        super.onCreate(savedInstanceState)
        binding = LoginConstraintBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setListeners()
    }


    private fun setListeners() {
        with(binding) {
            loginButton.setOnClickListener {
                val user = binding.editName.text.toString()
                val pass = binding.editPassword.text.toString()
                viewModel.login(user, pass)
            }
        }
    }

    private fun setObservers() {

    }
}









