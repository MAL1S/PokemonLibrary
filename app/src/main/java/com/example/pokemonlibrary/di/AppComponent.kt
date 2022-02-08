package com.example.pokemonlibrary.di

import android.content.Context
import com.example.pokemonlibrary.presentation.favorite.FavoriteFragment
import com.example.pokemonlibrary.presentation.main.MainActivity
import com.example.pokemonlibrary.presentation.random.RandomFragment
import com.example.pokemonlibrary.presentation.search.SearchFragment
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class, RepositoryModule::class, DatabaseModule::class])
interface AppComponent {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): AppComponent
    }

    fun inject(mainActivity: MainActivity)
    fun inject(searchFragment: SearchFragment)
    fun inject(randomFragment: RandomFragment)
    fun inject(favoriteFragment: FavoriteFragment)
}