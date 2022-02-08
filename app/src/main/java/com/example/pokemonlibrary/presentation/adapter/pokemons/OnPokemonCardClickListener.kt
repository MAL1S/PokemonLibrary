package com.example.pokemonlibrary.presentation.adapter.pokemons

import com.example.pokemonlibrary.domain.model.Pokemon

interface OnPokemonCardClickListener {

    fun onRemoveFromFavoriteClicked(pokemon: Pokemon, position: Int)
}