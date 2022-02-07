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

    private val _pokemonLiveData: MutableLiveData<Pokemon> = MutableLiveData()
    val pokemonLiveData: LiveData<Pokemon> = _pokemonLiveData

    fun getPokemonsByName(name: String) {
        getPokemonUseCase.get(name)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(getPokemonObserver())
    }

    private fun getPokemonObserver(): Observer<Pokemon> {
        return object : Observer<Pokemon> {
            override fun onSubscribe(d: Disposable) {

            }

            override fun onNext(t: Pokemon) {
                Log.d("AAA", "$t")
                _pokemonLiveData.postValue(t)
            }

            override fun onError(e: Throwable) {
                _pokemonLiveData.postValue(null)
            }

            override fun onComplete() {

            }
        }
    }
}