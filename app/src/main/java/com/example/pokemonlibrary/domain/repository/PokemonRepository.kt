package com.example.pokemonlibrary.domain.repository

import com.example.pokemonlibrary.domain.model.Pokemon
import com.example.pokemonlibrary.domain.model.PokemonId
import com.example.pokemonlibrary.domain.model.PokemonOverallData
import io.reactivex.Observable
import io.reactivex.Single

interface PokemonRepository {

    fun savePokemon(pokemon: Pokemon)

    fun removePokemonById(id: Int)

    fun savePokemonIdList(ids: List<PokemonId>)

    fun getPokemonIdList(): Single<List<PokemonId>>

    fun getPokemonByName(name: String): Observable<Pokemon>

    fun getPokemonById(id: Int): Single<Pokemon>

    fun getFavoritePokemonList(): Observable<List<Pokemon>>

    fun getPokemonsOverallData(offset: Int, limit: Int): Single<PokemonOverallData>
}