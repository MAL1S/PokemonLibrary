package com.example.pokemonlibrary.app

import android.app.Application
import com.example.pokemonlibrary.di.AppComponent
import com.example.pokemonlibrary.di.DaggerAppComponent

class App: Application() {

    val appComponent: AppComponent by lazy {
        DaggerAppComponent
            .factory()
            .create(applicationContext)
    }
}