package com.ojanbelajar.core.data.source.local.entity

import androidx.room.*

@Entity(tableName = "characters")
data class CharacterEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int,

    @ColumnInfo(name = "name")
    var name: String,

    @ColumnInfo(name= "status")
    var status: String,

    @ColumnInfo(name = "species")
    var species: String,

    @ColumnInfo(name = "type")
    var type: String,

    @ColumnInfo(name = "gender")
    var gender: String,

    @ColumnInfo(name = "origin")
    var origin: String,

    @ColumnInfo(name = "location")
    var location: String,

    @ColumnInfo(name= "image")
    var image: String,

    @ColumnInfo(name = "episode")
    val episode: Int,

    @ColumnInfo(name = "isFavorite")
    var isFavorite: Boolean = false
)