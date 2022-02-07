package com.example.pokemonlibrary.domain.model.converter

import androidx.room.TypeConverter
import com.example.pokemonlibrary.domain.model.PokemonAbility
import com.example.pokemonlibrary.domain.model.PokemonHeldItem
import com.example.pokemonlibrary.domain.model.PokemonStat

class HeldItemConverter {

    @TypeConverter
    fun fromHeldItem(forms: List<PokemonHeldItem>): String {
        return forms.joinToString(",")
    }

    @TypeConverter
    fun toHeldItem(formsString: String): List<PokemonHeldItem> {
        return formsString.split(",").map { PokemonHeldItem(PokemonStat(it)) }
    }
}