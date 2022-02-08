package com.example.pokemonlibrary.presentation.adapter.pokemons

import android.app.Activity
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.pokemonlibrary.domain.model.Pokemon

class PokemonRecyclerViewAdapter(
    private val pokemonList: List<Pokemon>,
    private val activity: Activity,
    private val onPokemonCardClickListener: OnPokemonCardClickListener
): RecyclerView.Adapter<PokemonRecyclerViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonRecyclerViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return PokemonRecyclerViewHolder(inflater, parent, activity, onPokemonCardClickListener)
    }

    override fun onBindViewHolder(holder: PokemonRecyclerViewHolder, position: Int) {
        val pokemon: Pokemon = pokemonList[position]
        holder.bind(pokemon)
    }

    override fun getItemCount(): Int = pokemonList.size
}