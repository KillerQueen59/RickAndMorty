package com.ojanbelajar.madeprojectojan.favourite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.ojanbelajar.core.ui.FavouriteAdapter
import com.ojanbelajar.madeprojectojan.detail.DetailCharacterActivity
import com.ojanbelajar.madeprojectojan.favourite.databinding.FragmentFavouriteBinding
import org.jetbrains.anko.support.v4.startActivity
import org.koin.core.context.loadKoinModules
import org.koin.android.viewmodel.ext.android.viewModel

class FavouriteFragment: Fragment() {

    private lateinit var binding: FragmentFavouriteBinding
    private val model: FavouriteViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFavouriteBinding.inflate(layoutInflater, container, false)
        loadKoinModules(favouriteModule)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val favouriteAdapter = FavouriteAdapter()
        favouriteAdapter.onItemClick = { data ->
            startActivity<DetailCharacterActivity>("data" to data)
        }
        with(binding.rvFav) {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = favouriteAdapter
        }
        getCharacterFavourite(favouriteAdapter)

    }

    private fun getCharacterFavourite(adapter: FavouriteAdapter) {
        model.character.observe(viewLifecycleOwner, { character ->
            adapter.setData(character)
        })
    }




}
