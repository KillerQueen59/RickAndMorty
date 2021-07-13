package com.ojanbelajar.core.domain.repository

import com.ojanbelajar.core.data.source.Resource
import com.ojanbelajar.core.domain.model.Character
import com.ojanbelajar.core.domain.model.Search
import kotlinx.coroutines.flow.Flow

interface ICharacterRepository {

    fun getCharacter(): Flow<Resource<List<Character>>>
    fun getSearch(name: String): Flow<Resource<List<Search>>>
    fun getFavouriteCharacter(): Flow<List<Character>>
    fun deleteSearch(character: Character)
    fun setFavouriteCharacter(character: Character,status: Boolean)
}