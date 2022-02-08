package com.example.pokemonlibrary.domain.model.converter

import androidx.room.TypeConverter
import com.example.pokemonlibrary.domain.model.PokemonStat

class FormsConverter {

    @TypeConverter
    fun fromForm(forms: List<PokemonStat>): String {
        return forms.joinToString(",")
    }

    @TypeConverter
    fun toForm(formsString: String): List<PokemonStat> {
        return formsString.split(",").map { PokemonStat(it) }
    }
}