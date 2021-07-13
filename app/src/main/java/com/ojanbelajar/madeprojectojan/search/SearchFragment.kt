package com.ojanbelajar.madeprojectojan.search

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.ojanbelajar.core.data.source.Resource
import com.ojanbelajar.core.ui.SearchAdapter
import com.ojanbelajar.madeprojectojan.databinding.FragmentSearchBinding
import com.ojanbelajar.madeprojectojan.detail.DetailCharacterActivity
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import org.jetbrains.anko.support.v4.startActivity
import org.jetbrains.anko.support.v4.toast
import org.koin.android.viewmodel.ext.android.viewModel

@ExperimentalCoroutinesApi
@FlowPreview
class SearchFragment : Fragment(){

    private lateinit var binding: FragmentSearchBinding
    private val model: SearchViewModel by viewModel()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSearchBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val searchAdapter = SearchAdapter()
        searchAdapter.onItemClick = { data ->
            startActivity<DetailCharacterActivity>("data" to data)
        }
        with(binding.rvSearch) {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = searchAdapter
        }
        binding.findName.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                newText?.let { model.setSearchQuery(it) }
                return true
            }
        })
        subscribeObservers(searchAdapter)
    }

    @ExperimentalCoroutinesApi
    private fun subscribeObservers(adapter: SearchAdapter) {
        model.searchResult.observe(viewLifecycleOwner, Observer {
            when(it){
                is Resource.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                }
                is Resource.Success -> {
                    binding.progressBar.visibility = View.GONE
                    adapter.setData(it.data)
                }
                is Resource.Error -> {
                    toast("Gagal memuat data")
                }
            }
        })
    }
}