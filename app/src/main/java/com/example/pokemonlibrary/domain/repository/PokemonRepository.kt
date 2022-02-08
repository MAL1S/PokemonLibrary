package com.example.pokemonlibrary.domain.repository

import com.example.pokemonlibrary.domain.model.Pokemon
import com.example.pokemonlibrary.domain.model.PokemonId
import com.example.pokemonlibrary.domain.model.PokemonOverallData
import io.reactivex.Observable
import io.reactivex.Single

interface PokemonRepository {

    fun savePokemon(pokemon: Pokemon)

    fun savePokemonIds(ids: List<PokemonId>)

    fun getPokemonByName(name: String): Observable<Pokemon>

    fun getPokemonById(id: Int): Single<Pokemon>

    fun getPokemonsOverallData(offset: Int, limit: Int): Single<PokemonOverallData>
}