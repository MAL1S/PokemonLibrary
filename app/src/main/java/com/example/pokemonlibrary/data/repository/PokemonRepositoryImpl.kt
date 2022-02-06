package com.example.pokemonlibrary.data.repository

import com.example.pokemonlibrary.data.remote.PokeApi
import com.example.pokemonlibrary.domain.model.Pokemon
import com.example.pokemonlibrary.domain.model.PokemonOverallData
import com.example.pokemonlibrary.domain.repository.PokemonRepository
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class PokemonRepositoryImpl @Inject constructor(
    private val pokeApi: PokeApi
): PokemonRepository {
    override fun getPokemonsByName(name: String): Observable<List<Pokemon>> {
        return pokeApi.getPokemonByName(name)
    }

    override fun getPokemonById(id: Int): Single<Pokemon> {
        return pokeApi.getPokemonById(id)
    }

    override fun getPokemonsOverallCount(): Single<PokemonOverallData> {
        return pokeApi.getPokemonOverallData()
    }
}