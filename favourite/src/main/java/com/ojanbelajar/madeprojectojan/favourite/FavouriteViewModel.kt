package com.ojanbelajar.madeprojectojan.favourite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.ojanbelajar.core.domain.usecase.CharacterUseCase

class FavouriteViewModel constructor(characterUseCase: CharacterUseCase): ViewModel() {
  val character = characterUseCase.getFavouriteCharacter().asLiveData()
}