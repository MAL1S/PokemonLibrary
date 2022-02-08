package com.example.pokemonlibrary.domain.model.converter

import androidx.room.TypeConverter
import com.example.pokemonlibrary.domain.model.PokemonHeldItem
import com.example.pokemonlibrary.domain.model.PokemonStat

class HeldItemConverter {

    @TypeConverter
    fun fromHeldItem(heldItems: List<PokemonHeldItem>): String {
        return heldItems.joinToString(",")
    }

    @TypeConverter
    fun toHeldItem(heldItemsString: String): List<PokemonHeldItem> {
        if (heldItemsString.isEmpty()) return emptyList()
        return heldItemsString.split(",").map {
            PokemonHeldItem(
                PokemonStat(it.substringAfter("name=").substringBefore(')'))
            )
        }
    }
}