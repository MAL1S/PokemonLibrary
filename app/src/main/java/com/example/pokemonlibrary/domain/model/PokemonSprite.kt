package com.example.pokemonlibrary.domain.model

import com.google.gson.annotations.SerializedName

data class PokemonSprite(
    @SerializedName("front_default") val frontDefault: String
)
