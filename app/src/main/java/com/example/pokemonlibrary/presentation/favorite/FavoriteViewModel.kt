package com.example.pokemonlibrary.presentation.favorite

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.pokemonlibrary.domain.model.Pokemon
import com.example.pokemonlibrary.domain.usecase.GetPokemonUseCase
import com.example.pokemonlibrary.domain.usecase.RemovePokemonUseCase
import io.reactivex.Observer
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class FavoriteViewModel @Inject constructor(
    private val getPokemonUseCase: GetPokemonUseCase,
    private val removePokemonUseCase: RemovePokemonUseCase
) : ViewModel() {

    private val _favoritePokemonListLiveData: MutableLiveData<List<Pokemon>> = MutableLiveData()
    val favoritePokemonListLiveData: LiveData<List<Pokemon>> = _favoritePokemonListLiveData

    fun getFavoritePokemonList() {
        getPokemonUseCase.getFavoritePokemonList()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(getFavoritePokemonListObserver())
    }

    fun removePokemonById(id: Int) {
        Single.just(id)
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.io())
            .subscribe({
                removePokemonUseCase.removePokemonById(it)
            }, {

            })
    }

    private fun getFavoritePokemonListObserver(): Observer<List<Pokemon>> {
        return object : Observer<List<Pokemon>> {
            override fun onSubscribe(d: Disposable) {

            }

            override fun onNext(t: List<Pokemon>) {
                Log.d("AAA", "LOADED")
                Log.d("BBB", "$t")
                _favoritePokemonListLiveData.postValue(t)
            }

            override fun onError(e: Throwable) {
                Log.d("AAA", "ERROR")
            }

            override fun onComplete() {

            }
        }
    }
}