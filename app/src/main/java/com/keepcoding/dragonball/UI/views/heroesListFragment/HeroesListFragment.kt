package com.keepcoding.dragonball.UI.views.heroesListFragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.keepcoding.dragonball.HomeActivity
import com.keepcoding.dragonball.UI.viewModels.HomeActivityViewModel
import com.keepcoding.dragonball.databinding.FragmentHeroesListBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HeroesListFragment : Fragment() {

    private val viewModel: HomeActivityViewModel by activityViewModels()
    private lateinit var binding: FragmentHeroesListBinding


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

    //AQUI IRA EL MAXIMO DE CODIGO
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //TODO: Desarrollar codigo
    }

    private fun createRecycler() {
        binding.recyclerView.adapter = HeroesAdapter(viewModel.heroesList)
        binding.recyclerView.layoutManager = LinearLayoutManager(binding.root.context)
    }

    //OBSERVADORES
    private fun setObservers() {
        viewModel.stateLiveDataHome.observe(viewLifecycleOwner) {
            when (it) {
                is HomeActivityViewModel.HeroesListState.Success -> {
                    Toast.makeText(binding.root.context, "HEROES CARGADOS", Toast.LENGTH_LONG).show()
                    lifecycleScope.launch(Dispatchers.Main) {
                        createRecycler()
                    }
                }

                is HomeActivityViewModel.HeroesListState.Error -> {
                    Toast.makeText(binding.root.context, "Error al cargar: ${it.error}", Toast.LENGTH_LONG).show()
                }

                is HomeActivityViewModel.HeroesListState.Loading -> {
                    Toast.makeText(binding.root.context, "CARGANDO...", Toast.LENGTH_LONG).show()
                }
                else -> {
                    Toast.makeText(
                        binding.root.context,
                        "NO HA ENTRADO EN NINGUNA DE LAS OPCIONES",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }
    }
}
