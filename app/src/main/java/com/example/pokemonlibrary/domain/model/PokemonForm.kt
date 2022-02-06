package com.example.pokemonlibrary.domain.model

import com.google.gson.annotations.SerializedName

data class PokemonForm(
    @SerializedName("url") val url: String
)
