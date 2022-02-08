package com.example.pokemonlibrary.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.pokemonlibrary.domain.model.Pokemon
import com.example.pokemonlibrary.domain.model.PokemonId
import io.reactivex.Observable
import io.reactivex.Single

@Dao
interface PokeDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPokemon(pokemon: Pokemon)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertIds(ids: List<PokemonId>)

    @Query("SELECT * FROM pokemon_id")
    fun getPokemonIds(): Single<List<PokemonId>>

    @Query("SELECT * FROM pokemon_table")
    fun getFavoritePokemonList(): Observable<List<Pokemon>>

    @Query("DELETE FROM pokemon_table WHERE id = :id")
    fun removePokemonById(id: Int)
}