package com.example.pokemonlibrary.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.pokemonlibrary.R
import com.example.pokemonlibrary.databinding.CardListItemPokemonBinding
import com.example.pokemonlibrary.domain.model.Pokemon

class PokemonRecyclerViewHolder(
    inflater: LayoutInflater,
    parent: ViewGroup
): RecyclerView.ViewHolder(inflater.inflate(R.layout.card_list_item_pokemon, parent, false)) {

    private val mBinding by viewBinding(CardListItemPokemonBinding::bind)

    fun bind(pokemon: Pokemon) {

    }
}