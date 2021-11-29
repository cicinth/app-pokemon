package com.app.apppokemon.components

import android.app.AlertDialog
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import retrofit2.Callback
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.app.apppokemon.databinding.ActivityImagePokemonBinding
import com.app.apppokemon.databinding.CardPokemonBinding
import com.app.apppokemon.gateway.PokemonGateway
import com.app.apppokemon.model.Pokemon
import com.app.apppokemon.model.PokemonDetails
import com.app.apppokemon.utils.NetworkUtils
import retrofit2.Call
import retrofit2.Response

class PokemonAdapter(
    private val context: Context,
    pokemon: List<Pokemon> = listOf()
): RecyclerView.Adapter<PokemonAdapter.ViewHolder>() {
    private val pokemons = pokemon.toMutableList()
     inner class ViewHolder(private val binding: CardPokemonBinding):
        RecyclerView.ViewHolder(binding.root){

            private val name = binding.namePokemon
            fun vincular(pokemon: Pokemon){
                binding.root.setOnClickListener {
                    getInfo(pokemon)
                }
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

    fun getInfo(pokemon: Pokemon){
        val arrayUrl = pokemon.url.split("/").toMutableList()
        arrayUrl.removeLast()
        val id = arrayUrl.last()
        getInfosPokemons(id)
    }

    fun getInfosPokemons(id: String){
        val retrofitClient = NetworkUtils.getRetrofitInstance()
        val endpoints = retrofitClient.create(PokemonGateway::class.java)

        val response = endpoints.getDetailsPokemon(id)

        val bindingImagePokemom = ActivityImagePokemonBinding.inflate(
            LayoutInflater.from(context)
        )

        response.enqueue(object : Callback<PokemonDetails>{
            override fun onResponse(
                call: Call<PokemonDetails>,
                response: Response<PokemonDetails>
            ) {
                val url = response.body()?.sprites?.image
                bindingImagePokemom.imageView.load(url)
                AlertDialog.Builder(context)
                    .setView(bindingImagePokemom.root)
                    .setPositiveButton("Ok"){_,_->}
                    .show()

            }

            override fun onFailure(call: Call<PokemonDetails>, t: Throwable) {
                Log.e("Retrive pokemon infos", t.message.toString() )
            }

        })

    }
}