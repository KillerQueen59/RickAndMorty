package com.ojanbelajar.core.data.source.local.room

import androidx.room.*
import com.ojanbelajar.core.data.source.local.entity.CharacterEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CharacterDao {

    @Query("SELECT * FROM characters")
    fun getCharacter(): Flow<List<CharacterEntity>>

    @Query("SELECT * FROM characters WHERE isFavorite = 1")
    fun getFavouriteCharacter(): Flow<List<CharacterEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE,entity = CharacterEntity::class)
    fun insertFavourite(character: List<CharacterEntity>)

    @Delete
    fun delete(character: CharacterEntity): Int

    @Update
    fun updateFavouriteCharacter(characterEntity: CharacterEntity)

}