package com.example.pokemonlibrary.domain.usecase

import com.example.pokemonlibrary.domain.model.PokemonOverallData
import com.example.pokemonlibrary.domain.repository.PokemonRepository
import io.reactivex.Single
import javax.inject.Inject

class GetPokemonDataUseCase @Inject constructor(
    private val repository: PokemonRepository
) {

    fun getPokemonCount(): Single<PokemonOverallData> = repository.getPokemonsOverallCount()
}