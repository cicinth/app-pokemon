package com.app.apppokemon.gateway

import com.app.apppokemon.model.PokemonDetails
import com.app.apppokemon.model.Pokemons
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface PokemonGateway {
    @GET("/api/v2/pokemon")
    fun getAllPokemons(): Call<Pokemons>

    @GET("api/v2/pokemon/{id}")
    fun getDetailsPokemon(
        @Path("id")
        id: String
    ): Call<PokemonDetails>
}