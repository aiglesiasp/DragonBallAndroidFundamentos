package com.keepcoding.dragonball.UI.views.battleFragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.keepcoding.dragonball.R
import com.keepcoding.dragonball.UI.viewModels.HomeActivityViewModel
import com.keepcoding.dragonball.UI.views.heroesListFragment.HeroesListFragment
import com.keepcoding.dragonball.databinding.FragmentBattleBinding
import com.keepcoding.dragonball.databinding.FragmentHeroesListBinding

class BattleFragment : Fragment() {

    companion object {
        fun newInstance() = BattleFragment()
    }

    private val viewModel: HomeActivityViewModel by viewModels()
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
        return binding.root
    }

    //AQUI IRA EL MAXIMO DE CODIGO
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //TODO: Desarrollar codigo
    }
}