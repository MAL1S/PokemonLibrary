package com.example.pokemonlibrary.presentation.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.pokemonlibrary.R
import com.example.pokemonlibrary.app.App
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var mViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.Theme_PokemonLibrary)
        setContentView(R.layout.activity_main)

        (application as App).appComponent.inject(this)

        mViewModel.updatePokemonIds()
    }
}
