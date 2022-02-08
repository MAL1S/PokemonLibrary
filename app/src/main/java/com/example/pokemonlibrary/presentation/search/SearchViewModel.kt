package com.example.pokemonlibrary.presentation.search

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.pokemonlibrary.domain.model.Pokemon
import com.example.pokemonlibrary.domain.usecase.GetPokemonDataUseCase
import com.example.pokemonlibrary.domain.usecase.GetPokemonUseCase
import com.example.pokemonlibrary.domain.usecase.SavePokemonUseCase
import io.reactivex.Observer
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class SearchViewModel @Inject constructor(
    private val getPokemonUseCase: GetPokemonUseCase,
    private val savePokemonUseCase: SavePokemonUseCase
): ViewModel() {

    private val _pokemonLiveData: MutableLiveData<Pokemon> = MutableLiveData()
    val pokemonLiveData: LiveData<Pokemon> = _pokemonLiveData

    fun getPokemonsByName(name: String) {
        getPokemonUseCase.get(name)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(getPokemonObserver())
    }

    fun addPokemonToFavorite(pokemon: Pokemon) {
        val savePokemonSingleSubscribe = Single.just(pokemon)
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.io())
            .subscribe({
                savePokemonUseCase.savePokemon(it)
            }, {

            })
    }

    private fun getPokemonObserver(): Observer<Pokemon> {
        return object : Observer<Pokemon> {
            override fun onSubscribe(d: Disposable) {

            }

            override fun onNext(t: Pokemon) {
                Log.d("AAA", "LOADED")
                _pokemonLiveData.postValue(t)
            }

            override fun onError(e: Throwable) {
                Log.d("AAA", "ERROR")
                _pokemonLiveData.postValue(null)
            }

            override fun onComplete() {

            }
        }
    }
}