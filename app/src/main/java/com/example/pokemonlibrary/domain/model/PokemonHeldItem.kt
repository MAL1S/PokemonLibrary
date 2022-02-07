package com.example.pokemonlibrary.domain.model

import com.google.gson.annotations.SerializedName

data class PokemonHeldItem(
    @SerializedName("item") val item: PokemonStat
)
