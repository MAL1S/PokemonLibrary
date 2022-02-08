package com.example.pokemonlibrary.domain.usecase

import com.example.pokemonlibrary.data.local.PokeDao
import javax.inject.Inject

class RemovePokemonUseCase @Inject constructor(
    private val pokeDao: PokeDao
) {

    fun removePokemonById(id: Int) {
        pokeDao.removePokemonById(id)
    }
}