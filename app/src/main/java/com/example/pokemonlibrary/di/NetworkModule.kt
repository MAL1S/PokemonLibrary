package com.example.pokemonlibrary.di

import com.example.pokemonlibrary.data.remote.PokeApi
import com.example.pokemonlibrary.data.remote.PokeApiClient
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class NetworkModule {

    @Singleton
    @Provides
    fun provideRetrofitClient(): PokeApi {
        return PokeApiClient.getClient()
    }
}