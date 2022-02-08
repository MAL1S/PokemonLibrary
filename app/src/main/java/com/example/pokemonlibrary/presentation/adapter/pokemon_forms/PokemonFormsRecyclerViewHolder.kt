package com.example.pokemonlibrary.presentation.adapter.pokemon_forms

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.pokemonlibrary.R
import com.example.pokemonlibrary.databinding.CardItemPokemonFormsBinding

class PokemonFormsRecyclerViewHolder(
    inflater: LayoutInflater,
    parent: ViewGroup
): RecyclerView.ViewHolder(inflater.inflate(R.layout.card_item_pokemon_forms, parent, false)) {

    private val mBinding by viewBinding(CardItemPokemonFormsBinding::bind)

    fun bind(form: String) {
        mBinding.tvItemPokemonForm.text = form
    }
}