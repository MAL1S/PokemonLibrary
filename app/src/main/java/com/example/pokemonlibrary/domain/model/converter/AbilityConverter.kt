package com.example.pokemonlibrary.domain.model.converter

import androidx.room.TypeConverter
import com.example.pokemonlibrary.domain.model.PokemonAbility
import com.example.pokemonlibrary.domain.model.PokemonStat

class AbilityConverter {

    @TypeConverter
    fun fromAbility(forms: List<PokemonAbility>): String {
        return forms.joinToString(",")
    }

    @TypeConverter
    fun toAbility(formsString: String): List<PokemonAbility> {
        return formsString.split(",").map { PokemonAbility(PokemonStat(it)) }
    }
}