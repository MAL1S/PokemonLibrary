package com.example.pokemonlibrary.domain.model

import com.google.gson.annotations.SerializedName

data class PokemonListItem(
    @SerializedName("name") val name: String,
    @SerializedName("url") val url: String
)
