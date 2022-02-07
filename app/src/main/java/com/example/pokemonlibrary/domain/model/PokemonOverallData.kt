package com.example.pokemonlibrary.domain.model

import com.google.gson.annotations.SerializedName

data class PokemonOverallData(
    @SerializedName("count") val count: Int,
    @SerializedName("results") val pokemons: List<PokemonListItem>
)
