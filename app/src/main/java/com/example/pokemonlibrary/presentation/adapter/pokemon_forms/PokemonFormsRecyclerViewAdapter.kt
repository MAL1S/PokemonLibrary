package com.example.pokemonlibrary.presentation.adapter.pokemon_forms

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class PokemonFormsRecyclerViewAdapter(
    private val stats: List<String>
): RecyclerView.Adapter<PokemonFormsRecyclerViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonFormsRecyclerViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return PokemonFormsRecyclerViewHolder(inflater, parent)
    }

    override fun onBindViewHolder(holder: PokemonFormsRecyclerViewHolder, position: Int) {
        val stat: String = stats[position]
        holder.bind(stat)
    }

    override fun getItemCount(): Int = stats.size
}