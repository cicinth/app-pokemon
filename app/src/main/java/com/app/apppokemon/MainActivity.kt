package com.app.apppokemon

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import retrofit2.Callback
import com.app.apppokemon.components.PokemonAdapter
import com.app.apppokemon.databinding.ActivityMainBinding
import com.app.apppokemon.gateway.PokemonGateway
import com.app.apppokemon.model.Pokemon
import com.app.apppokemon.model.Pokemons
import com.app.apppokemon.utils.NetworkUtils
import retrofit2.Call
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setContentView(binding.root)
        val cards = binding.cardsPokemon
        cards.layoutManager = GridLayoutManager(this, 3)
        getData()
    }

    fun configRecicleView(pokemons: List<Pokemon>){
        val cardPokemonView = binding.cardsPokemon
        cardPokemonView.adapter = PokemonAdapter(this, pokemons )
    }
    fun getData(){
        val retrofitClient = NetworkUtils.getRetrofitInstance()
        val endpoints = retrofitClient.create(PokemonGateway::class.java)

        val response = endpoints.getAllPokemons()

        response.enqueue(object  : Callback<Pokemons> {
            override fun onResponse(call: Call<Pokemons>, response: Response<Pokemons>) {
                val pokemons = response.body()?.results
                if (pokemons != null) {
                    configRecicleView(pokemons)
                }
            }

            override fun onFailure(call: Call<Pokemons>, t: Throwable) {
               Toast.makeText(baseContext,t.message, Toast.LENGTH_LONG).show()
            }

        } )
    }
}