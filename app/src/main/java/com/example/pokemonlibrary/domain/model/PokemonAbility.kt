package com.example.pokemonlibrary.domain.model

import com.google.gson.annotations.SerializedName

data class PokemonAbility(
    @SerializedName("ability") val ability: PokemonStat
)