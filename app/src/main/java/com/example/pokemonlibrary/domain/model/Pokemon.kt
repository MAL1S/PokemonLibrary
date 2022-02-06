package com.example.pokemonlibrary.domain.model

import com.google.gson.annotations.SerializedName

data class Pokemon(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("base_experience") val baseExperience: Int,
    @SerializedName("height") val height: Int,
    @SerializedName("weight") val weight: Int,
    @SerializedName("sprites") val sprite: PokemonSprite,
    @SerializedName("forms") val forms: List<PokemonForm>
)
