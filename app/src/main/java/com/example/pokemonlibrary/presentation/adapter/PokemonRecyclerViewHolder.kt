package com.example.pokemonlibrary.presentation.adapter

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bumptech.glide.Glide
import com.example.pokemonlibrary.R
import com.example.pokemonlibrary.databinding.CardItemPokemonFormsBinding
import com.example.pokemonlibrary.databinding.CardListItemPokemonBinding
import com.example.pokemonlibrary.domain.model.Pokemon
import com.example.pokemonlibrary.domain.model.PokemonForm

class PokemonRecyclerViewHolder(
    inflater: LayoutInflater,
    parent: ViewGroup
): RecyclerView.ViewHolder(inflater.inflate(R.layout.card_item_pokemon_forms, parent, false)) {

    private val mBinding by viewBinding(CardItemPokemonFormsBinding::bind)

    fun bind(form: String) {
        mBinding.tvItemPokemonForm.text = form
    }


}