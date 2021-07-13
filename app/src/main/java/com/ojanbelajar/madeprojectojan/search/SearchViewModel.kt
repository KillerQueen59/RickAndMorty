package com.ojanbelajar.madeprojectojan.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.ojanbelajar.core.domain.usecase.CharacterUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.channels.ConflatedBroadcastChannel
import kotlinx.coroutines.flow.*


@FlowPreview
class SearchViewModel (private val characterUseCase: CharacterUseCase): ViewModel() {

    @ExperimentalCoroutinesApi
    val queryChannel = ConflatedBroadcastChannel<String>()


    @ExperimentalCoroutinesApi
    fun setSearchQuery(search:String){
        queryChannel.offer(search)
    }

    @ExperimentalCoroutinesApi
    val searchResult = queryChannel.asFlow()
        .debounce(300)
        .distinctUntilChanged()
        .filter {
            it.trim().isNotEmpty()
        }
        .flatMapLatest { search ->
            characterUseCase.getSearch("https://rickandmortyapi.com/api/character/?name=$search")
        }.asLiveData()
}