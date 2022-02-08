package com.example.pokemonlibrary.data.repository

import com.example.pokemonlibrary.data.local.PokeDao
import com.example.pokemonlibrary.data.remote.PokeApi
import com.example.pokemonlibrary.domain.model.Pokemon
import com.example.pokemonlibrary.domain.model.PokemonId
import com.example.pokemonlibrary.domain.model.PokemonOverallData
import com.example.pokemonlibrary.domain.repository.PokemonRepository
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class PokemonRepositoryImpl @Inject constructor(
    private val pokeApi: PokeApi,
    private val pokeDao: PokeDao
): PokemonRepository {

    override fun savePokemon(pokemon: Pokemon) {
        return pokeDao.insertPokemon(pokemon)
    }

    override fun removePokemonById(id: Int) {
        pokeDao.removePokemonById(id)
    }

    override fun savePokemonIdList(ids: List<PokemonId>) {
        return pokeDao.insertIds(ids)
    }

    override fun getPokemonIdList(): Single<List<PokemonId>> {
        return pokeDao.getPokemonIds()
    }

    override fun getPokemonByName(name: String): Observable<Pokemon> {
        return pokeApi.getPokemonByName(name)
    }

    override fun getPokemonById(id: Int): Single<Pokemon> {
        return pokeApi.getPokemonById(id)
    }

    override fun getFavoritePokemonList(): Observable<List<Pokemon>> {
        return pokeDao.getFavoritePokemonList()
    }

    override fun getPokemonsOverallData(offset: Int, limit: Int): Single<PokemonOverallData> {
        return pokeApi.getPokemonOverallData(offset = offset, limit = limit)
    }
}