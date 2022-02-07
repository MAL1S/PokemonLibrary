package com.example.pokemonlibrary.presentation.search

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bumptech.glide.Glide
import com.example.pokemonlibrary.R
import com.example.pokemonlibrary.app.App
import com.example.pokemonlibrary.databinding.FragmentSearchBinding
import com.example.pokemonlibrary.domain.model.Pokemon
import com.example.pokemonlibrary.domain.model.PokemonStat
import com.example.pokemonlibrary.presentation.adapter.PokemonRecyclerViewAdapter
import com.jakewharton.rxbinding2.widget.RxTextView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class SearchFragment : Fragment() {

    @Inject
    lateinit var mViewModel: SearchViewModel

    private val mBinding by viewBinding(FragmentSearchBinding::bind)

    private lateinit var mAdapterForms: PokemonRecyclerViewAdapter
    private lateinit var mAdapterAbilities: PokemonRecyclerViewAdapter
    private lateinit var mAdapterHeldItems: PokemonRecyclerViewAdapter

    private lateinit var subscribe: Disposable

    override fun onAttach(context: Context) {
        super.onAttach(context)

        (requireActivity().application as App).appComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initVariables()
        initObservers()
    }

    private fun initVariables() {
    }

    private fun initObservers() {
        subscribe = RxTextView.textChanges(mBinding.etSearchPokemonName)
            .debounce(500, TimeUnit.MILLISECONDS)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                mViewModel.getPokemonsByName(it.toString().lowercase())
            }, {
                Log.d("pokemon_list_search", "${it.message}")
            })

        mViewModel.pokemonLiveData.observe(viewLifecycleOwner) {
            if (it != null && it.id != 0) {
                bindCardView(it)
            } else if (mBinding.etSearchPokemonName.text.toString().isEmpty()) {
                mBinding.textInputLayoutSearchPokemonName.error = ""
                mBinding.searchCardLayout.visibility = View.INVISIBLE
                mBinding.ivSearchNothingFound.visibility = View.VISIBLE
            } else {
                mBinding.searchCardLayout.visibility = View.INVISIBLE
                mBinding.ivSearchNothingFound.visibility = View.VISIBLE
                mBinding.textInputLayoutSearchPokemonName.error = "Not found"
            }
        }
    }

    private fun bindCardView(pokemon: Pokemon) {
        mBinding.textInputLayoutSearchPokemonName.error = ""
        mBinding.ivSearchNothingFound.visibility = View.INVISIBLE
        mBinding.searchCardLayout.visibility = View.VISIBLE
        
        mBinding.searchCardPokemon.tvPokemonName.text = pokemon.name
        mBinding.searchCardPokemon.tvPokemonBaseExp.text = "Base exp: ${pokemon.baseExperience}"

        mBinding.searchCardPokemon.tvPokemonWeight.text = pokemon.weight.toString()
        mBinding.searchCardPokemon.tvPokemonHeight.text = pokemon.height.toString()

        //forms
        mAdapterForms = PokemonRecyclerViewAdapter(
            pokemon.forms?.map { it.name } ?: listOf("No forms")
        )
        mBinding.searchCardPokemon.rcvPokemonForms.adapter = mAdapterForms
        mAdapterForms.notifyDataSetChanged()

        //abilities
        mAdapterAbilities = PokemonRecyclerViewAdapter(
            pokemon.abilities?.map { it.ability.name } ?: listOf("No abilities")
        )
        mBinding.searchCardPokemon.rcvPokemonAbilities.adapter = mAdapterAbilities
        mAdapterAbilities.notifyDataSetChanged()

        //held items
        mAdapterHeldItems = PokemonRecyclerViewAdapter(
            pokemon.heldItems?.map { it.item.name } ?: listOf("No abilities")
        )
        mBinding.searchCardPokemon.rcvPokemonHeldItems.adapter = mAdapterHeldItems
        mAdapterHeldItems.notifyDataSetChanged()

        loadPokemonImage(pokemon.sprite.frontDefault)
    }

    private fun loadPokemonImage(url: String) {
        Glide
            .with(requireActivity())
            .load(url)
            .into(mBinding.searchCardPokemon.ivPokemonImage)
    }

    override fun onDestroyView() {
        subscribe.dispose()
        mViewModel.pokemonLiveData.removeObservers(viewLifecycleOwner)
        super.onDestroyView()
    }
}