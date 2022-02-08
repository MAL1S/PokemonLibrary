package com.example.pokemonlibrary.domain.model.converter

import androidx.room.TypeConverter
import com.example.pokemonlibrary.domain.model.PokemonAbility
import com.example.pokemonlibrary.domain.model.PokemonStat

class AbilityConverter {

    @TypeConverter
    fun fromAbility(abilities: List<PokemonAbility>): String {
        return abilities.joinToString(",")
    }

    @TypeConverter
    fun toAbility(abilitiesString: String): List<PokemonAbility> {
        if (abilitiesString.isEmpty()) return emptyList()
        return abilitiesString.split(",").map {
            PokemonAbility(
                ability = PokemonStat(it.substringAfter("name=").substringBefore(')'))
            )
        }
    }
}

//string = PokemonAbility(ability=PokemonStat(name=magma-armor)
//list = PokemonAbility(ability=PokemonStat())