package com.example.pokemonlibrary.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.pokemonlibrary.domain.model.Pokemon
import com.example.pokemonlibrary.domain.model.PokemonId

@Database(entities = [Pokemon::class, PokemonId::class], version = 1)
abstract class PokeDB: RoomDatabase() {
    abstract fun getPokeDao(): PokeDao
}