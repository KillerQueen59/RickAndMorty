package com.ojanbelajar.core.data.source.local.room

import androidx.room.*
import com.ojanbelajar.core.data.source.local.entity.CharacterEntity

@Database(entities = [CharacterEntity::class],version = 6 ,exportSchema = false)
abstract class CharacterDatabase: RoomDatabase() {

    abstract fun characterDao():CharacterDao
}