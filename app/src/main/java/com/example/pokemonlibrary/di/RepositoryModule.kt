package com.example.pokemonlibrary.di

import com.example.pokemonlibrary.data.local.PokeDao
import com.example.pokemonlibrary.data.remote.PokeApi
import com.example.pokemonlibrary.data.repository.PokemonRepositoryImpl
import com.example.pokemonlibrary.domain.repository.PokemonRepository
import dagger.Module
import dagger.Provides

@Module
class RepositoryModule {

    @Provides
    fun providePokemonRepository(pokeApi: PokeApi, pokeDao: PokeDao): PokemonRepository {
        return PokemonRepositoryImpl(pokeApi = pokeApi, pokeDao = pokeDao)
    }
}