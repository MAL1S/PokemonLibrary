package com.example.pokemonlibrary.presentation.adapter

import android.app.Activity
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.pokemonlibrary.domain.model.Pokemon
import com.example.pokemonlibrary.domain.model.PokemonForm

class PokemonRecyclerViewAdapter(
    private val forms: List<PokemonForm>
): RecyclerView.Adapter<PokemonRecyclerViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonRecyclerViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return PokemonRecyclerViewHolder(inflater, parent)
    }

    override fun onBindViewHolder(holder: PokemonRecyclerViewHolder, position: Int) {
        val form: String = forms[position].name
        holder.bind(form)
    }

    override fun getItemCount(): Int = forms.size

}