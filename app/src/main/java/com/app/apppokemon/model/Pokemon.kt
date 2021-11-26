package com.app.apppokemon.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
data class Pokemons(
    @JsonProperty("results")
    val results: List<Pokemon>
)

data class Pokemon(
    @JsonProperty("name")
    val name: String,
    @JsonProperty("url")
    val url: String
)
