package com.app.apppokemon.components

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.apppokemon.databinding.CardPokemonBinding
import com.app.apppokemon.model.Pokemon

class PokemonAdapter(
    private val context: Context,
    pokemon: List<Pokemon> = listOf()
): RecyclerView.Adapter<PokemonAdapter.ViewHolder>() {
    private val pokemons = pokemon.toMutableList()
    class ViewHolder(private val binding: CardPokemonBinding):
        RecyclerView.ViewHolder(binding.root){
            private val name = binding.namePokemon

            fun vincular(pokemon: Pokemon){
                name.text = pokemon.name
            }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = CardPokemonBinding
            .inflate(
                LayoutInflater.from(context),
                parent,
                false
            )

        return  ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
       val pokemon = pokemons[position]
        holder.vincular(pokemon)
    }

    override fun getItemCount(): Int = pokemons.size
}