package com.keepcoding.dragonball.UI.views.heroesListFragment

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.util.Base64
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


class HeroesAdapter(private val heroesList: List<Hero>): RecyclerView.Adapter<HeroesAdapter.HeroesViewHolder> ()
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
        //TODO: pasar elementos a imprimir
    }


    //Se crea asi siempre
    inner class HeroesViewHolder(val binding: HeroesItemBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(hero: Hero) {
            with(binding) {
                nombreHeroe.text = hero.name
                progressBar.max = hero.maxLive
                progressBar.progress = hero.currentLive
                Glide
                    .with(root)
                    .load(hero.photo)
                    .centerCrop()
                    .placeholder(R.drawable.background_heroes_image)
                    .into(imagenHeroe)

            }

            binding.root.setOnClickListener {
                val activity = binding.root.context as HomeActivity
                activity.supportFragmentManager.beginTransaction()
                    .replace(R.id.contenedor, BattleFragment())
                    .commit()
            }
        }
    }


}

