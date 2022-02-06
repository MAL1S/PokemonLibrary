package com.example.pokemonlibrary.data.remote

import com.example.pokemonlibrary.domain.model.Pokemon
import com.example.pokemonlibrary.domain.model.PokemonOverallData
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface PokeApi {

    @GET("pokemon/{name}")
    fun getPokemonByName(@Path("name") name: String): Observable<List<Pokemon>>

    @GET("pokemon/{id}")
    fun getPokemonById(@Path("id") id: Int): Single<Pokemon>

    @GET("pokemon")
    fun getPokemonOverallData(): Single<PokemonOverallData>
}