package com.ojanbelajar.madeprojectojan.favourite

import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val favouriteModule = module {
    viewModel { FavouriteViewModel(get()) } }
