package com.ojanbelajar.core.data.source


import com.ojanbelajar.core.data.source.local.LocalDataSource
import com.ojanbelajar.core.data.source.remote.RemoteDataSource
import com.ojanbelajar.core.data.source.remote.network.ApiResponse
import com.ojanbelajar.core.data.source.remote.response.CharacterResponse
import com.ojanbelajar.core.domain.model.Character
import com.ojanbelajar.core.domain.model.Search
import com.ojanbelajar.core.domain.repository.ICharacterRepository
import com.ojanbelajar.core.utils.AppExecutors
import com.ojanbelajar.core.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


class CharacterRepository (
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
): ICharacterRepository{

    override fun getCharacter(): Flow<Resource<List<Character>>> =
        object : NetworkBoundResource<List<Character>, List<CharacterResponse>>() {
            override fun loadFromDB(): Flow<List<Character>> {
                return localDataSource.getCharacter().map {
                    DataMapper.mapEntitiesToDomain(it)
                }
            }

            override fun shouldFetch(data: List<Character>?): Boolean = data == null || data.isEmpty()

            override suspend fun createCall(): Flow<ApiResponse<List<CharacterResponse>>> =
                remoteDataSource.getCharacter()

            override suspend fun saveCallResult(data: List<CharacterResponse>) {
                val list = DataMapper.mapResponsesToEntities(data)
                appExecutors.diskIO().execute{
                    localDataSource.insertCharacter(list)
                }
            }
        }.asFlow()

    override fun getSearch(name: String): Flow<Resource<List<Search>>> =
        object : NetworkOnlyResource<List<Search>, List<CharacterResponse>>() {
            override fun loadFromNetwork(data: List<CharacterResponse>): Flow<List<Search>> {
                return DataMapper.mapResponsesToDomainSearch(data)
            }

            override suspend fun createCall(): Flow<ApiResponse<List<CharacterResponse>>> {
                return remoteDataSource.getSearch(name)
            }

        }.asFlow()

    override fun getFavouriteCharacter(): Flow<List<Character>> {
        return localDataSource.getFavouriteCharacter().map{
            DataMapper.mapEntitiesToDomain(it)
        }
    }

    override fun deleteSearch(character: Character) {
        appExecutors.diskIO().execute{
            val characterEntity = DataMapper.mapDomainToEntity(character)
            localDataSource.delete(characterEntity)
        }
    }


    override fun setFavouriteCharacter(character: Character, status: Boolean) {
        appExecutors.diskIO().execute{
            val characterEntity = DataMapper.mapDomainToEntity(character)
            localDataSource.setFavouriteCharacter(characterEntity,status)
        }
    }
}