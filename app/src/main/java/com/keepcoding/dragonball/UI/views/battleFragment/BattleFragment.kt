package com.keepcoding.dragonball.UI.views.battleFragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.keepcoding.dragonball.HomeActivity
import com.keepcoding.dragonball.R
import com.keepcoding.dragonball.UI.viewModels.HomeActivityViewModel
import com.keepcoding.dragonball.databinding.FragmentBattleBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class BattleFragment : Fragment() {

    private val viewModel: HomeActivityViewModel by activityViewModels()
    private lateinit var binding: FragmentBattleBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentBattleBinding.inflate(inflater)
        //PREPARO VIEW CON LOS HEROES
        prepareToBattle()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //BOTON DE LUCHAR
        binding.buttonFight.setOnClickListener {
            val fightFinished = viewModel.fight()
            reloadLifeProgressBar()
            if(fightFinished) returnToHeroesList()
        }
    }


    //FUNCION PARA PINTAR HEROES EN EL FRAGMENT
    private fun prepareToBattle() {
        //OBTENER HEROES
        val hero1 = viewModel.listHeroesFighting[0]
        val hero2 = viewModel.listHeroesFighting[1]

        binding.nombreJugador1.text = hero1.name
        binding.nombreJugador2.text = hero2.name
        Glide
            .with(this)
            .load(hero1.photo)
            .centerCrop()
            .placeholder(R.drawable.background_heroes_image)
            .into(binding.imagenJugador1)
        Glide
            .with(this)
            .load(hero2.photo)
            .centerCrop()
            .placeholder(R.drawable.background_heroes_image)
            .into(binding.imagenJugador2)
    }

    private fun returnToHeroesList() {
        val activity = activity as HomeActivity
        activity.supportFragmentManager
            .popBackStack()
    }

    private fun reloadLifeProgressBar() {
        binding.progressBar1.progress = viewModel.listHeroesFighting[0].currentLive
        binding.progressBar2.progress = viewModel.listHeroesFighting[1].currentLive
    }

}