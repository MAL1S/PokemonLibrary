package com.example.pokemonlibrary.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.pokemonlibrary.domain.model.PokemonStat

class PokemonRecyclerViewAdapter(
    private val stats: List<String>
): RecyclerView.Adapter<PokemonRecyclerViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonRecyclerViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return PokemonRecyclerViewHolder(inflater, parent)
    }

    override fun onBindViewHolder(holder: PokemonRecyclerViewHolder, position: Int) {
        val stat: String = stats[position]
        holder.bind(stat)
    }

    override fun getItemCount(): Int = stats.size

    fun getList() = stats
}