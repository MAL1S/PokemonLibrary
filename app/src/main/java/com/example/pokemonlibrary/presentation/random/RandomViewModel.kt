package com.example.pokemonlibrary.presentation.random

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.pokemonlibrary.domain.model.Pokemon
import com.example.pokemonlibrary.domain.usecase.GetPokemonUseCase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class RandomViewModel @Inject constructor(
    private val getPokemonUseCase: GetPokemonUseCase
): ViewModel() {

    private val _randomPokemonLiveData: MutableLiveData<Pokemon> = MutableLiveData()
    val randomPokemonLiveData: LiveData<Pokemon> = _randomPokemonLiveData

    fun updateRandomPokemon() {
        getPokemonUseCase.getPokemonIds()
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.io())
            .subscribe({
                getRandomPokemon(it[(it.indices).random()].id)
            }, {

            })
    }

    private fun getRandomPokemon(id: Int) {
        getPokemonUseCase.get(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                _randomPokemonLiveData.postValue(it)
            }, {

            })
    }
}