package com.keepcoding.dragonball.UI.views.heroesListFragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.keepcoding.dragonball.R
import com.keepcoding.dragonball.UI.viewModels.HomeActivityViewModel
import com.keepcoding.dragonball.databinding.FragmentHeroesListBinding

class HeroesListFragment : Fragment() {

    companion object {
        fun newInstance() = HeroesListFragment()
    }

    private val viewModel: HomeActivityViewModel by viewModels()
    private lateinit var binding : FragmentHeroesListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding = FragmentHeroesListBinding.inflate(inflater)
        return binding.root
    }

    //AQUI IRA EL MAXIMO DE CODIGO
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //TODO: Desarrollar codigo
    }
}