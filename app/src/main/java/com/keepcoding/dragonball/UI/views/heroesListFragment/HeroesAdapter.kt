package com.keepcoding.dragonball.UI.views.heroesListFragment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.keepcoding.dragonball.databinding.HeroesItemBinding


class HeroesAdapter: RecyclerView.Adapter<HeroesAdapter.HeroesViewHolder> (){


    //Se crea asi siempre
    inner class HeroesViewHolder(val binding: HeroesItemBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int) {
            binding.nombreHeroe.text = "Name $position"
            binding.vidaHeroeTitulo.text = "VIDA de ${binding.nombreHeroe.text}"
        }
    }


    //Crear viewHolder que usaremos. SIEMPRE SE HACE IGUAL
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HeroesViewHolder {
        return  HeroesViewHolder(
            HeroesItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }


    // Le pasamos el holder y la posicion de la lista donde estoy. Aqui puedo modificar los parametros
    override fun onBindViewHolder(holder: HeroesViewHolder, position: Int) {
        holder.bind(position)
    }

    //NUMERO DE ELEMENTOS QUE CONTENDRA LA LISTA
    override fun getItemCount(): Int {
        return 40
        //TODO: pasar elementos a imprimir
    }

}