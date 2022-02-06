package com.example.pokemonlibrary.domain.repository

import com.example.pokemonlibrary.domain.model.Pokemon
import com.example.pokemonlibrary.domain.model.PokemonOverallData
import io.reactivex.Observable
import io.reactivex.Single

interface PokemonRepository {

    fun getPokemonsByName(name: String): Observable<List<Pokemon>>

    fun getPokemonById(id: Int): Single<Pokemon>

    fun getPokemonsOverallCount(): Single<PokemonOverallData>
}