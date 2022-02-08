package com.example.pokemonlibrary.domain.model

import com.google.gson.annotations.SerializedName

data class PokemonStat(
    @SerializedName("name") val name: String
)
