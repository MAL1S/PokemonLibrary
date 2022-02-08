package com.example.pokemonlibrary.domain.usecase

import android.util.Log
import com.example.pokemonlibrary.domain.model.PokemonOverallData
import com.example.pokemonlibrary.domain.repository.PokemonRepository
import io.reactivex.Single
import javax.inject.Inject

class GetPokemonDataUseCase @Inject constructor(
    private val repository: PokemonRepository
) {

    fun getPokemonOverallData(offset: Int, limit: Int): Single<PokemonOverallData> {
        return repository.getPokemonsOverallData(offset = offset, limit = limit)
    }
}