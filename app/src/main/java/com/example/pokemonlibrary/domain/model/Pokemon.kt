package com.example.pokemonlibrary.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.pokemonlibrary.domain.model.converter.AbilityConverter
import com.example.pokemonlibrary.domain.model.converter.FormsConverter
import com.example.pokemonlibrary.domain.model.converter.HeldItemConverter
import com.example.pokemonlibrary.domain.model.converter.SpriteConverter
import com.google.gson.annotations.SerializedName

@Entity(tableName = "pokemon_table")
@TypeConverters(
    SpriteConverter::class,
    FormsConverter::class,
    AbilityConverter::class,
    HeldItemConverter::class
)
data class Pokemon(
    @SerializedName("id") @PrimaryKey val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("base_experience") val baseExperience: Int,
    @SerializedName("height") val height: Int,
    @SerializedName("weight") val weight: Int,
    @SerializedName("sprites") val sprite: PokemonSprite,
    @SerializedName("forms") val forms: List<PokemonStat>?,
    @SerializedName("abilities") val abilities: List<PokemonAbility>?,
    @SerializedName("held_items") val heldItems: List<PokemonHeldItem>
)