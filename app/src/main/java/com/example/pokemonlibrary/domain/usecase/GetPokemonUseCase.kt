package com.example.pokemonlibrary.domain.usecase

import com.example.pokemonlibrary.domain.model.Pokemon
import com.example.pokemonlibrary.domain.repository.PokemonRepository
import io.reactivex.Observable
import io.reactivex.Single
import javax.inject.Inject

class GetPokemonUseCase @Inject constructor(
    private val repository: PokemonRepository
) {

    fun get(name: String): Observable<List<Pokemon>> = repository.getPokemonsByName(name = name)

    fun get(id: Int): Single<Pokemon> = repository.getPokemonById(id = id)
}