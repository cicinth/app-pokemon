package com.app.apppokemon.utils

import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory

internal class NetworkUtils {
    companion object{
        fun getRetrofitInstance(): Retrofit {
            return Retrofit.Builder()
                .baseUrl(path)
                .addConverterFactory(JacksonConverterFactory.create())
                .build()
        }

        val path = "https://pokeapi.co"
    }
}