package com.example.pokemonlibrary.di

import com.example.pokemonlibrary.data.remote.PokeApi
import com.example.pokemonlibrary.data.remote.PokeApiClient
import dagger.Module
import dagger.Provides

@Module
class NetworkModule {

    @Provides
    fun provideRetrofitClient(): PokeApi {
        return PokeApiClient.getClient()
    }
}