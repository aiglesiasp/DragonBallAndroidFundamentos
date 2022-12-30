package com.keepcoding.dragonball.UI.views.battleFragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.keepcoding.dragonball.HomeActivity
import com.keepcoding.dragonball.R
import com.keepcoding.dragonball.UI.viewModels.HomeActivityViewModel
import com.keepcoding.dragonball.UI.views.heroesListFragment.HeroesListFragment
import com.keepcoding.dragonball.databinding.FragmentBattleBinding
import com.keepcoding.dragonball.databinding.FragmentHeroesListBinding

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

        //CREO FUNCION ESCUCHA BOTON PARA LA LUCHA
        //BOTON DE LUCHAR
        binding.buttonFight.setOnClickListener {
            viewModel.fight()
            reloadLifeProgressBar()
        }

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

        return binding.root
    }

    //AQUI IRA EL MAXIMO DE CODIGO
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //TODO: Desarrollar codigo
    }

    private fun returnToPreviousFragment() {
        val activity = activity as HomeActivity
        activity.supportFragmentManager
            .popBackStack()
    }

    private fun reloadLifeProgressBar() {
        binding.progressBar1.progress = viewModel.listHeroesFighting[0].currentLive
        binding.progressBar2.progress = viewModel.listHeroesFighting[1].currentLive
    }
}