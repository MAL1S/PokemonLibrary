package com.example.pokemonlibrary.presentation.search

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.pokemonlibrary.data.remote.PokeApi
import com.example.pokemonlibrary.domain.model.Pokemon
import com.example.pokemonlibrary.domain.usecase.GetPokemonUseCase
import com.jakewharton.rxbinding2.widget.RxTextView
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class SearchViewModel @Inject constructor(
    private val getPokemonUseCase: GetPokemonUseCase
): ViewModel() {

    private val _pokemonListLiveData: MutableLiveData<List<Pokemon>> = MutableLiveData()
    val pokemonListLiveData: LiveData<List<Pokemon>> = _pokemonListLiveData

    fun getPokemonsByName(name: String, rxTextView: RxTextView) {
        getPokemonUseCase.get(name)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(getPokemonListObserver())
    }

    private fun getPokemonListObserver(): Observer<List<Pokemon>> {
        return object : Observer<List<Pokemon>> {
            override fun onSubscribe(d: Disposable) {}

            override fun onNext(t: List<Pokemon>) {
                _pokemonListLiveData.postValue(t)
            }

            override fun onError(e: Throwable) {
                Log.d("pokemon_list_search", "${e.message}")
            }

            override fun onComplete() {}
        }
    }
}