package com.example.pokemonlibrary.domain.usecase

import com.example.pokemonlibrary.data.local.PokeDao
import com.example.pokemonlibrary.domain.model.Pokemon
import com.example.pokemonlibrary.domain.model.PokemonId
import com.example.pokemonlibrary.domain.repository.PokemonRepository
import io.reactivex.Single
import javax.inject.Inject

class SavePokemonUseCase @Inject constructor(
    private val pokeDao: PokeDao
) {

    fun savePokemon(pokemon: Pokemon) {
        pokeDao.insertPokemon(pokemon)
    }

    fun savePokemonIds(ids: List<PokemonId>) {
        pokeDao.insertIds(ids)
    }
}