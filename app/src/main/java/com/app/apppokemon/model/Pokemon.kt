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

@JsonIgnoreProperties(ignoreUnknown = true)
data class PokemonDetails(
    @JsonProperty("sprites")
    val sprites: Sprites,
    @JsonProperty("abilities")
    val abilities: List<Abilities>
)

@JsonIgnoreProperties(ignoreUnknown = true)
data class Sprites(
    @JsonProperty("front_default")
    val image: String
)

@JsonIgnoreProperties(ignoreUnknown = true)
data class Abilities(
    @JsonProperty("ability")
    val ability: Ability
)

@JsonIgnoreProperties(ignoreUnknown = true)
data class Ability(
    @JsonProperty("name")
    val name: String
)
