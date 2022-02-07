package com.example.pokemonlibrary.domain.model

import com.google.gson.annotations.SerializedName

data class PokemonForm(
    @SerializedName("name") val name: String
)
