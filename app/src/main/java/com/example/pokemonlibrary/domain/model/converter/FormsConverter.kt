package com.example.pokemonlibrary.domain.model.converter

import androidx.room.TypeConverter
import com.example.pokemonlibrary.domain.model.PokemonForm

class FormsConverter {

    @TypeConverter
    fun fromForm(forms: List<PokemonForm>): String {
        return forms.joinToString(",")
    }

    @TypeConverter
    fun toForm(formsString: String): List<PokemonForm> {
        return formsString.split(",").map { PokemonForm(it) }
    }
}