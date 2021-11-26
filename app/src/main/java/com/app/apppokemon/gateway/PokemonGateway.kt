package com.app.apppokemon.gateway

import com.app.apppokemon.model.Pokemons
import retrofit2.Call
import retrofit2.http.GET

interface PokemonGateway {
    @GET("/api/v2/pokemon")
    fun getAllPokemons(): Call<Pokemons>
}