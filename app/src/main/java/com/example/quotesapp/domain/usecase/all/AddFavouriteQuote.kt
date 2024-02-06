package com.example.quotesapp.domain.usecase.all

import com.example.quotesapp.domain.model.Favourite
import com.example.quotesapp.domain.repository.FavouriteRepository
import javax.inject.Inject

class AddFavouriteQuote @Inject constructor(
    private val favouriteRepository: FavouriteRepository
){

    suspend operator fun invoke(favourite: Favourite){
        favouriteRepository.upsertFavourite(favourite)
    }

}