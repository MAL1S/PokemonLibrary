package com.example.pokemonlibrary.presentation.adapter.pokemons

import android.app.Activity
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bumptech.glide.Glide
import com.example.pokemonlibrary.R
import com.example.pokemonlibrary.databinding.CardItemPokemonFormsBinding
import com.example.pokemonlibrary.databinding.CardPokemonBinding
import com.example.pokemonlibrary.domain.model.Pokemon
import com.example.pokemonlibrary.domain.model.PokemonAbility
import com.example.pokemonlibrary.domain.model.PokemonStat
import com.example.pokemonlibrary.presentation.adapter.pokemon_forms.PokemonFormsRecyclerViewAdapter

class PokemonRecyclerViewHolder(
    inflater: LayoutInflater,
    parent: ViewGroup,
    private val activity: Activity,
    private val onPokemonCardClickListener: OnPokemonCardClickListener
) : RecyclerView.ViewHolder(inflater.inflate(R.layout.card_pokemon, parent, false)) {

    private lateinit var mAdapterForms: PokemonFormsRecyclerViewAdapter
    private lateinit var mAdapterAbilities: PokemonFormsRecyclerViewAdapter
    private lateinit var mAdapterHeldItems: PokemonFormsRecyclerViewAdapter

    private val mBinding by viewBinding(CardPokemonBinding::bind)

    fun bind(pokemon: Pokemon) {
        mBinding.tvPokemonName.text = pokemon.name.replaceFirstChar { it.uppercase() }
        mBinding.tvPokemonBaseExp.text = activity.getString(
            R.string.card_exp,
            pokemon.baseExperience
        )

        mBinding.tvPokemonWeight.text = pokemon.weight.toString()
        mBinding.tvPokemonHeight.text = pokemon.height.toString()

        mBinding.btnCardAddInFavorite.setIconResource(R.drawable.ic_favorite_outlined_24dp)

        mBinding.btnCardAddInFavorite.text = activity.getString(R.string.card_btn_favorite_remove)
        mBinding.btnCardAddInFavorite.setOnClickListener {
            onPokemonCardClickListener.onRemoveFromFavoriteClicked(
                pokemon = pokemon,
                position = absoluteAdapterPosition
            )
        }

        //forms
        val pokemonForms = pokemon.forms?.map { it.name }
        mAdapterForms = PokemonFormsRecyclerViewAdapter(
            pokemonForms ?: listOf(activity.getString(R.string.card_pokemon_forms_none))
        )
        mBinding.rcvPokemonForms.adapter = mAdapterForms
        mAdapterForms.notifyDataSetChanged()

        //abilities
        val pokemonAbilities = pokemon.abilities?.map { it.ability.name }
        mAdapterAbilities = PokemonFormsRecyclerViewAdapter(
            pokemonAbilities ?: listOf(activity.getString(R.string.card_pokemon_abilities_none))
        )
        mBinding.rcvPokemonAbilities.adapter = mAdapterAbilities
        mAdapterAbilities.notifyDataSetChanged()

        //held items
        val pokemonHeldItems = pokemon.heldItems.map { it.item.name }
        mAdapterHeldItems = PokemonFormsRecyclerViewAdapter(
            pokemonHeldItems.ifEmpty { listOf(activity.getString(R.string.card_pokemon_held_items_none)) }
        )
        mBinding.rcvPokemonHeldItems.adapter = mAdapterHeldItems
        mAdapterHeldItems.notifyDataSetChanged()

        loadPokemonImage(pokemon.sprite.frontDefault)
    }

    private fun loadPokemonImage(url: String) {
        Glide
            .with(activity)
            .load(url)
            .into(mBinding.ivPokemonImage)
    }
}