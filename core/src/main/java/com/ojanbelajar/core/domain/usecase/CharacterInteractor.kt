package com.ojanbelajar.core.domain.usecase


import com.ojanbelajar.core.domain.model.Character
import com.ojanbelajar.core.domain.repository.ICharacterRepository
import kotlinx.coroutines.flow.Flow


class CharacterInteractor (private val characterRepository: ICharacterRepository): CharacterUseCase {
    override fun getCharacter() = characterRepository.getCharacter()
    override fun getSearch(name: String) = characterRepository.getSearch(name)
    override fun getFavouriteCharacter(): Flow<List<Character>> = characterRepository.getFavouriteCharacter()
    override fun deleteSearch(character: Character) = characterRepository.deleteSearch(character)
    override  fun setFavouriteCharacter(character: Character, status: Boolean) = characterRepository.setFavouriteCharacter(character,status)
}