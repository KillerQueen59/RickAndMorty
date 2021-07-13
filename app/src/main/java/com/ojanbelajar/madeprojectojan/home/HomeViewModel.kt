package com.ojanbelajar.madeprojectojan.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.ojanbelajar.core.domain.usecase.CharacterUseCase

class HomeViewModel  (
   characterUseCase: CharacterUseCase
): ViewModel() {
    val character = characterUseCase.getCharacter().asLiveData()
}