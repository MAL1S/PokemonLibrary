package com.example.pokemonlibrary.domain.model.converter

import androidx.room.TypeConverter
import com.example.pokemonlibrary.domain.model.PokemonSprite

class SpriteConverter {

    @TypeConverter
    fun fromSprite(sprite: PokemonSprite): String {
        return sprite.frontDefault
    }

    @TypeConverter
    fun toSprite(spriteString: String): PokemonSprite {
        return PokemonSprite(frontDefault = spriteString)
    }
}