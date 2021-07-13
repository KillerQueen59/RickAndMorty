package com.ojanbelajar.madeprojectojan.detail

import androidx.lifecycle.ViewModel
import com.ojanbelajar.core.domain.model.Character
import com.ojanbelajar.core.domain.usecase.CharacterUseCase


class DetailViewModel (private val characterUseCase: CharacterUseCase) :ViewModel(){
    fun setFavouriteCharacter(character: Character,status: Boolean) =
        characterUseCase.setFavouriteCharacter(character,status)


}