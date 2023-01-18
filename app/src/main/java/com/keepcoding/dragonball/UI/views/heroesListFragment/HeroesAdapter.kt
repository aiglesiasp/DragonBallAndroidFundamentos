package com.keepcoding.dragonball.UI.views.heroesListFragment

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.util.Base64
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.keepcoding.dragonball.Hero
import com.keepcoding.dragonball.HomeActivity
import com.keepcoding.dragonball.R
import com.keepcoding.dragonball.UI.viewModels.HomeActivityViewModel
import com.keepcoding.dragonball.UI.views.battleFragment.BattleFragment
import com.keepcoding.dragonball.databinding.HeroesItemBinding


class HeroesAdapter(private val heroesList: List<Hero>, private val viewModel: HomeActivityViewModel?= null): RecyclerView.Adapter<HeroesAdapter.HeroesViewHolder> ()
{

    //Crear viewHolder que usaremos. SIEMPRE SE HACE IGUAL
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HeroesViewHolder {
        return  HeroesViewHolder(
            HeroesItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    // Le pasamos el holder y la posicion de la lista donde estoy. Aqui puedo modificar los parametros
    override fun onBindViewHolder(holder: HeroesViewHolder, position: Int) {
        holder.bind(heroesList[position])
    }

    //NUMERO DE ELEMENTOS QUE CONTENDRA LA LISTA
    override fun getItemCount(): Int {
        return heroesList.size
    }


    //Se crea asi siempre
    inner class HeroesViewHolder(val binding: HeroesItemBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(hero: Hero) {
            with(binding) {
                nombreHeroe.text = hero.name
                progressBarLife.max = hero.maxLive
                progressBarLife.progress = hero.currentLive
                Glide
                    .with(root)
                    .load(hero.photo)
                    .centerCrop()
                    .placeholder(R.drawable.background_heroes_image)
                    .into(imagenHeroe)

            }

            //Navegar a la batalla
            binding.root.setOnClickListener {
                Log.d("Battle Fragment", "Navigate to Battle Fragment")
                var prepareToBattle = false
                viewModel?.let {
                    prepareToBattle = it.selectedHeroesForBattle(hero)
                }
                if(prepareToBattle) {
                    val activity = binding.root.context as HomeActivity
                    activity.supportFragmentManager.beginTransaction()
                        .addToBackStack("battle")
                        .replace(R.id.contenedor, BattleFragment())
                        .commit()
                }
            }
        }
    }


}

