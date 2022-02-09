package com.example.pokemonlibrary.presentation.random

import android.content.Context
import android.graphics.drawable.Drawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.example.pokemonlibrary.R
import com.example.pokemonlibrary.app.App
import com.example.pokemonlibrary.databinding.FragmentRandomBinding
import com.example.pokemonlibrary.domain.model.Pokemon
import com.example.pokemonlibrary.presentation.adapter.pokemon_forms.PokemonFormsRecyclerViewAdapter
import com.example.pokemonlibrary.presentation.favorite.FavoriteViewModel
import com.example.pokemonlibrary.presentation.search.SearchViewModel
import javax.inject.Inject

class RandomFragment : Fragment() {

    private val mBinding by viewBinding(FragmentRandomBinding::bind)

    @Inject
    lateinit var mViewModel: RandomViewModel

    @Inject
    lateinit var favoriteViewModel: FavoriteViewModel

    private lateinit var mAdapterForms: PokemonFormsRecyclerViewAdapter
    private lateinit var mAdapterAbilities: PokemonFormsRecyclerViewAdapter
    private lateinit var mAdapterHeldItems: PokemonFormsRecyclerViewAdapter

    private var currentPokemon: Pokemon? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)

        (requireActivity().application as App).appComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_random, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initListeners()
        initObservers()

        mViewModel.updateRandomPokemon()
    }

    private fun initListeners() {
        mBinding.btnRandomPokemon.setOnClickListener {
            mBinding.randomCardLayout.visibility = View.GONE
            mViewModel.updateRandomPokemon()
        }
        mBinding.randomCardPokemon.btnCardAddInFavorite.setOnClickListener {
            if (currentPokemon != null) {
                favoriteViewModel.addPokemonToFavorite(pokemon = currentPokemon!!)
            }
        }
    }

    private fun initObservers() {
        mViewModel.randomPokemonLiveData.observe(viewLifecycleOwner) {
            bindCardView(it)
        }
    }

    private fun bindCardView(pokemon: Pokemon) {
        currentPokemon = pokemon

        mBinding.randomCardPokemon.tvPokemonName.text = pokemon.name.replaceFirstChar { it.uppercase() }
        mBinding.randomCardPokemon.tvPokemonBaseExp.text = getString(
            R.string.card_exp,
            pokemon.baseExperience
        )

        mBinding.randomCardPokemon.tvPokemonWeight.text = pokemon.weight.toString()
        mBinding.randomCardPokemon.tvPokemonHeight.text = pokemon.height.toString()

        //forms
        mAdapterForms = PokemonFormsRecyclerViewAdapter(
            pokemon.forms?.map { it.name } ?: listOf(getString(R.string.card_pokemon_forms_none))
        )
        mBinding.randomCardPokemon.rcvPokemonForms.adapter = mAdapterForms
        mAdapterForms.notifyDataSetChanged()

        //abilities
        mAdapterAbilities = PokemonFormsRecyclerViewAdapter(
            pokemon.abilities?.map { it.ability.name } ?: listOf(getString(R.string.card_pokemon_abilities_none))
        )
        mBinding.randomCardPokemon.rcvPokemonAbilities.adapter = mAdapterAbilities
        mAdapterAbilities.notifyDataSetChanged()

        //held items
        val pokemonHeldItems = pokemon.heldItems.map { it.item.name }
        mAdapterHeldItems = PokemonFormsRecyclerViewAdapter(
            pokemonHeldItems.ifEmpty { listOf(getString(R.string.card_pokemon_held_items_none)) }
        )
        mBinding.randomCardPokemon.rcvPokemonHeldItems.adapter = mAdapterHeldItems
        mAdapterHeldItems.notifyDataSetChanged()

        loadPokemonImage(pokemon.sprite.frontDefault)
    }

    private fun loadPokemonImage(url: String) {
        Glide
            .with(requireActivity())
            .load(url)
            .listener(object: RequestListener<Drawable> {
                override fun onResourceReady(
                    resource: Drawable?,
                    model: Any?,
                    target: Target<Drawable>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
                    mBinding.randomCardLayout.visibility = View.VISIBLE

                    return false
                }

                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Drawable>?,
                    isFirstResource: Boolean
                ): Boolean {
                    return false
                }

            })
            .into(mBinding.randomCardPokemon.ivPokemonImage)
    }
}