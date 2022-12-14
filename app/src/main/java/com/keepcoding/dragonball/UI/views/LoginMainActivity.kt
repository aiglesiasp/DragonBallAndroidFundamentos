package com.keepcoding.dragonball

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
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
        setObservers()
        setListeners()
    }

    //LLAMAR AL LOGIN DEL VIEWMODEL
    private fun login() {
        val user = binding.editName.text.toString()
        val pass = binding.editPassword.text.toString()
        viewModel.login(user,pass)
    }


    //ESCUCHADORES
    private fun setListeners() {
        with(binding) {
            loginButton.setOnClickListener {
                login()
            }
        }
    }

    //OBSERVADORES
    private fun setObservers() {
        viewModel.stateLiveDataLogin.observe(this) {
            when(it) {
                is LoginMainActivityViewModel.LoginState.Success -> {
                    binding.progressBarLogin?.visibility = View.INVISIBLE
                    HomeActivity.launch(this, viewModel.token)
                }

                is LoginMainActivityViewModel.LoginState.Error -> {
                    binding.progressBarLogin?.visibility = View.INVISIBLE
                    Toast.makeText(this, "Error al cargar: ${it.error}", Toast.LENGTH_LONG).show()
                }

                is LoginMainActivityViewModel.LoginState.Loading -> {
                    binding.progressBarLogin?.visibility = View.VISIBLE
                }
                else -> {
                    Toast.makeText(this, "NO HA ENTRADO EN NINGUNA DE LAS OPCIONES", Toast.LENGTH_LONG).show()
                }
            }
        }
    }
}









