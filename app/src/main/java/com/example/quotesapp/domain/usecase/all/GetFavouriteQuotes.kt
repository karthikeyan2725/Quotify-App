package com.example.quotesapp.domain.usecase.all

import com.example.quotesapp.domain.model.Favourite
import com.example.quotesapp.domain.repository.FavouriteRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetFavouriteQuotes @Inject constructor(
    private val favouriteRepository: FavouriteRepository
){

     operator fun invoke(): Flow<List<Favourite>> {
        return favouriteRepository.getFavourites()
    }

}