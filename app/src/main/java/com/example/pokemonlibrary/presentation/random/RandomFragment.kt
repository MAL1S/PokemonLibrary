package com.example.pokemonlibrary.presentation.random

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bumptech.glide.Glide
import com.example.pokemonlibrary.R
import com.example.pokemonlibrary.app.App
import com.example.pokemonlibrary.databinding.FragmentRandomBinding
import com.example.pokemonlibrary.domain.model.Pokemon
import com.example.pokemonlibrary.presentation.adapter.pokemon_forms.PokemonFormsRecyclerViewAdapter
import com.example.pokemonlibrary.presentation.search.SearchViewModel
import javax.inject.Inject

class RandomFragment : Fragment() {

    private val mBinding by viewBinding(FragmentRandomBinding::bind)

    @Inject
    lateinit var mViewModel: RandomViewModel

    @Inject
    lateinit var searchViewModel: SearchViewModel

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
            mViewModel.updateRandomPokemon()
        }
        mBinding.randomCardPokemon.btnCardAddInFavorite.setOnClickListener {
            if (currentPokemon != null) {
                searchViewModel.addPokemonToFavorite(pokemon = currentPokemon!!)
            }
        }
    }

    private fun initObservers() {
        mViewModel.randomPokemonLiveData.observe(viewLifecycleOwner) {
            bindCardView(it)
            mBinding.randomCardLayout.visibility = View.VISIBLE
        }
    }

    private fun bindCardView(pokemon: Pokemon) {
        currentPokemon = pokemon

        mBinding.randomCardPokemon.tvPokemonName.text = pokemon.name.replaceFirstChar { it.uppercase() }
        mBinding.randomCardPokemon.tvPokemonBaseExp.text = "Base exp: ${pokemon.baseExperience}"

        mBinding.randomCardPokemon.tvPokemonWeight.text = pokemon.weight.toString()
        mBinding.randomCardPokemon.tvPokemonHeight.text = pokemon.height.toString()

        //forms
        mAdapterForms = PokemonFormsRecyclerViewAdapter(
            pokemon.forms?.map { it.name } ?: listOf("No forms")
        )
        mBinding.randomCardPokemon.rcvPokemonForms.adapter = mAdapterForms
        mAdapterForms.notifyDataSetChanged()

        //abilities
        mAdapterAbilities = PokemonFormsRecyclerViewAdapter(
            pokemon.abilities?.map { it.ability.name } ?: listOf("No abilities")
        )
        mBinding.randomCardPokemon.rcvPokemonAbilities.adapter = mAdapterAbilities
        mAdapterAbilities.notifyDataSetChanged()

        //held items
        val pokemonHeldItems = pokemon.heldItems.map { it.item.name }
        mAdapterHeldItems = PokemonFormsRecyclerViewAdapter(
            pokemonHeldItems.ifEmpty { listOf("No held items") }
        )
        mBinding.randomCardPokemon.rcvPokemonHeldItems.adapter = mAdapterHeldItems
        mAdapterHeldItems.notifyDataSetChanged()

        loadPokemonImage(pokemon.sprite.frontDefault)
    }

    private fun loadPokemonImage(url: String) {
        Glide
            .with(requireActivity())
            .load(url)
            .into(mBinding.randomCardPokemon.ivPokemonImage)
    }
}