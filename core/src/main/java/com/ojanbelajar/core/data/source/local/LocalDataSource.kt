package com.ojanbelajar.core.data.source.local

import com.ojanbelajar.core.data.source.local.entity.CharacterEntity
import com.ojanbelajar.core.data.source.local.room.CharacterDao
import kotlinx.coroutines.flow.Flow


class LocalDataSource (private val characterDao: CharacterDao) {

    fun getCharacter(): Flow<List<CharacterEntity>> = characterDao.getCharacter()

    fun getFavouriteCharacter(): Flow<List<CharacterEntity>> = characterDao.getFavouriteCharacter()

    fun insertCharacter(characterList: List<CharacterEntity>) = characterDao.insertFavourite(characterList)

    fun delete(characterEntity: CharacterEntity) = characterDao.delete(characterEntity)

    fun setFavouriteCharacter(characterEntity: CharacterEntity, status: Boolean){
        characterEntity.isFavorite = status
        characterDao.updateFavouriteCharacter(characterEntity)
    }
}