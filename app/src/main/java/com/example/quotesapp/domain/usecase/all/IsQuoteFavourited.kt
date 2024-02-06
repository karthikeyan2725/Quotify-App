package com.example.quotesapp.domain.usecase.all

import com.example.quotesapp.domain.repository.FavouriteRepository
import javax.inject.Inject

class IsQuoteFavourited @Inject constructor(
    private val favouriteRepository: FavouriteRepository
) {
    suspend operator fun invoke(quoteId:String):Boolean{
        return favouriteRepository.getFavouriteWithId(quoteId) != null
    }
}