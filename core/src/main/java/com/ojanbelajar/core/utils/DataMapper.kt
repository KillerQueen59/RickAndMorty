package com.ojanbelajar.core.utils


import com.ojanbelajar.core.data.source.local.entity.CharacterEntity
import com.ojanbelajar.core.data.source.remote.response.CharacterResponse
import com.ojanbelajar.core.domain.model.Character
import com.ojanbelajar.core.domain.model.Location
import com.ojanbelajar.core.domain.model.Origin
import com.ojanbelajar.core.domain.model.Search
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

object DataMapper {
    fun mapResponsesToEntities(input: List<CharacterResponse>): List<CharacterEntity> {
        val tourismList = ArrayList<CharacterEntity>()
        input.map {
            val tourism =CharacterEntity(
                id = it.id,
                name = it.name,
                status = it.status,
                species = it.species,
                type = it.type,
                gender = it.gender,
                origin = it.origin.name,
                location = it.location.name,
                image = it.image,
                episode = it.episode.size
            )
            tourismList.add(tourism)
        }
        return tourismList
    }

    fun mapEntitiesToDomain(input: List<CharacterEntity>): List<Character> =
        input.map {
            Character(
                id = it.id,
                name = it.name,
                status = it.status,
                species = it.species,
                type = it.type,
                gender = it.gender,
                origin = Origin(it.origin),
                location = Location(it.location),
                image = it.image,
                episode = it.episode,
                isFavourite =  it.isFavorite
            )
        }

    fun mapDomainToEntity(input: Character) =
        com.ojanbelajar.core.data.source.local.entity.CharacterEntity(
            id = input.id,
            name = input.name,
            status = input.status,
            species = input.species,
            type = input.type,
            gender = input.gender,
            origin = input.origin.name,
            location = input.location.name,
            image = input.image,
            episode = input.episode,
            isFavorite = input.isFavourite
        )

    fun mapResponsesToDomain(input: List<CharacterResponse>): Flow<List<Character>> {
        val dataArray = ArrayList<Character>()
        input.map {
            val character = Character(
                it.id,
                it.name,
                it.status,
                it.species,
                it.type,
                it.gender,
                Origin(it.origin.name),
                Location(it.location.name),
                it.image,
                it.episode.size,
                false
            )
            dataArray.add(character)
        }
        return flowOf(dataArray)
    }


    fun mapResponsesToDomainSearch(input: List<CharacterResponse>): Flow<List<Search>> {
        val dataArray = ArrayList<Search>()
        input.map {
            val character = Search(
                it.id,
                it.name,
                it.status,
                it.species,
                it.type,
                it.gender,
                Origin(it.origin.name),
                Location(it.location.name),
                it.image,
                it.episode.size,
                false
            )
            dataArray.add(character)
        }
        return flowOf(dataArray)
    }
}