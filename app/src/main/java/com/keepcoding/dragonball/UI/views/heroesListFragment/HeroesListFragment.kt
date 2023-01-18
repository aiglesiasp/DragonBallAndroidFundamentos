package com.keepcoding.dragonball.UI.views.heroesListFragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.keepcoding.dragonball.UI.viewModels.HomeActivityViewModel
import com.keepcoding.dragonball.databinding.FragmentHeroesListBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HeroesListFragment : Fragment() {

    private val viewModel: HomeActivityViewModel by activityViewModels()
    private lateinit var binding: FragmentHeroesListBinding


    //LLAMO A LOS HEROES CUANDO CREO FRAGMENT
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getHeroesList()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHeroesListBinding.inflate(inflater)
        with(binding.root) {
            setObservers()
            return this
        }
    }


    // CREADOR DEL RECYCLER
    private fun createRecycler() {
        binding.recyclerView.adapter = HeroesAdapter(viewModel.heroesList, viewModel)
        binding.recyclerView.layoutManager = LinearLayoutManager(binding.root.context)
    }

    //OBSERVADORES
    private fun setObservers() {
        viewModel.stateLiveDataHeroes.observe(viewLifecycleOwner) {
            when (it) {
                is HomeActivityViewModel.HeroesListState.Success -> {
                    binding.progressBarHeroes.visibility = View.INVISIBLE
                    lifecycleScope.launch(Dispatchers.Main) {
                        createRecycler()
                    }
                }

                is HomeActivityViewModel.HeroesListState.Error -> {
                    binding.progressBarHeroes.visibility = View.INVISIBLE
                    Toast.makeText(binding.root.context, "Error al cargar: ${it.error}", Toast.LENGTH_SHORT).show()
                }

                is HomeActivityViewModel.HeroesListState.Loading -> {
                    binding.progressBarHeroes.visibility = View.VISIBLE
                }
                else -> {
                    Toast.makeText(
                        binding.root.context,"NO HA ENTRADO EN NINGUNA DE LAS OPCIONES", Toast.LENGTH_SHORT).show()
                }
            }
        }

        viewModel.stateFightData.observe(viewLifecycleOwner) {
            when(it) {
                is HomeActivityViewModel.FightListState.Success -> {
                    Toast.makeText(binding.root.context, "EL GANADOR ES:  ${it.heroe.name}", Toast.LENGTH_LONG).show()
                }
                is HomeActivityViewModel.FightListState.Error -> {
                    Toast.makeText(binding.root.context, "LO SENTIMOS,  ${it.error}", Toast.LENGTH_LONG).show()
                }

            }
        }
    }
}
