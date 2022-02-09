package com.example.pokemonlibrary.presentation.main

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.pokemonlibrary.domain.model.Pokemon
import com.example.pokemonlibrary.domain.model.PokemonId
import com.example.pokemonlibrary.domain.model.PokemonOverallData
import com.example.pokemonlibrary.domain.model.PokemonSprite
import com.example.pokemonlibrary.domain.usecase.GetPokemonDataUseCase
import com.example.pokemonlibrary.domain.usecase.GetPokemonUseCase
import com.example.pokemonlibrary.domain.usecase.SavePokemonUseCase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val savePokemonUseCase: SavePokemonUseCase,
    private val getPokemonUseCase: GetPokemonUseCase,
    private val getPokemonDataUseCase: GetPokemonDataUseCase
) : ViewModel() {

    fun updatePokemonIds() {
        val updatePokemonIdsSubscribe = getPokemonDataUseCase
            .getPokemonOverallData(0, 0)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                savePokemonIds(it)
            }, {
                Log.d("pokemon_id_update", "error1 = ${it}")
            })
    }

    private fun savePokemonIds(it: PokemonOverallData) {
        val savePokemonIdsSubscribe = getPokemonDataUseCase
            .getPokemonOverallData(0, it.count)
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.io())
            .subscribe({ overallData ->
                savePokemonUseCase.savePokemonIds(overallData.pokemons.map { pokemon ->
                    PokemonId(
                        pokemon.url.substring(
                            0, pokemon.url.length - 1
                        ).split("/").last().toInt()
                    )
                })
            }, {
                Log.d("pokemon_id_update", "error2 = ${it}")
            })
    }
}
