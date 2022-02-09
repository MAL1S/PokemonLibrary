package com.example.pokemonlibrary.presentation.favorite

import android.content.Context
import android.os.Bundle
import android.os.Parcelable
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.pokemonlibrary.R
import com.example.pokemonlibrary.app.App
import com.example.pokemonlibrary.databinding.FragmentFavoriteBinding
import com.example.pokemonlibrary.domain.model.Pokemon
import com.example.pokemonlibrary.presentation.adapter.pokemons.OnPokemonCardClickListener
import com.example.pokemonlibrary.presentation.adapter.pokemons.PokemonRecyclerViewAdapter
import javax.inject.Inject

class FavoriteFragment : Fragment(), OnPokemonCardClickListener {

    private val mBinding by viewBinding(FragmentFavoriteBinding::bind)

    @Inject
    lateinit var mViewModel: FavoriteViewModel

    private lateinit var mAdapter: PokemonRecyclerViewAdapter

    private var rcvState: Parcelable? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)

        (requireActivity().application as App).appComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorite, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initListeners()
        initObservers()

        mViewModel.getFavoritePokemonList()
    }

    private fun initListeners() {

    }

    private fun initObservers() {
        mViewModel.favoritePokemonListLiveData.observe(viewLifecycleOwner) {
            mAdapter = PokemonRecyclerViewAdapter(it, requireActivity(), this)
            mBinding.rcvFavoritePokemon.adapter = mAdapter
            mAdapter.notifyDataSetChanged()

            if (it.isEmpty()) {
                mBinding.ivPokemonListEmpty.visibility = View.VISIBLE
                mBinding.rcvFavoritePokemon.visibility = View.GONE
            } else {
                mBinding.ivPokemonListEmpty.visibility = View.GONE
                mBinding.rcvFavoritePokemon.visibility = View.VISIBLE
            }
            mBinding.rcvFavoritePokemon.layoutManager?.onRestoreInstanceState(rcvState)
        }
    }

    override fun onRemoveFromFavoriteClicked(pokemon: Pokemon, position: Int) {
        rcvState = mBinding.rcvFavoritePokemon.layoutManager?.onSaveInstanceState()
        mViewModel.removePokemonById(pokemon.id)
        mAdapter.notifyItemRemoved(position)
    }
}
