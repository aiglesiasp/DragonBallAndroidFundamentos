package com.keepcoding.dragonball

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.keepcoding.dragonball.UI.viewModels.HomeActivityViewModel
import com.keepcoding.dragonball.UI.views.battleFragment.BattleFragment
import com.keepcoding.dragonball.UI.views.heroesListFragment.HeroesListFragment
import com.keepcoding.dragonball.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    private val viewModel: HomeActivityViewModel by viewModels()

    //PARA NAVEGAR ENTRE ACTIVITIES
    companion object {
        private const val TAG_TOKEN = "token"
        fun launch(context: Context, text: String) {
            val intent = Intent(context, HomeActivity::class.java)
            intent.putExtra(TAG_TOKEN, text)
            context.startActivity(intent)
        }
    }

    //ON CREATE
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Obtener el token que me pasan del otro activity
        if(savedInstanceState == null) {
            //OBTENER TOKEN
            intent.getStringExtra(TAG_TOKEN)?.let {
                viewModel.token = it
            }
            //NAVEGAR AL FRAGMENT
            supportFragmentManager.beginTransaction()
                .replace(binding.contenedor.id, BattleFragment.newInstance())
                .commitNow()
        }
    }
}