package com.example.quotesapp.domain.usecase

import com.example.quotesapp.domain.usecase.all.AddFavouriteQuote
import com.example.quotesapp.domain.usecase.all.DeleteFavouriteQuote
import com.example.quotesapp.domain.usecase.all.GetFavouriteQuotes
import com.example.quotesapp.domain.usecase.all.IsQuoteFavourited

data class FavouritesUseCases(
    val addFavouriteQuote: AddFavouriteQuote,
    val deleteFavouriteQuote: DeleteFavouriteQuote,
    val getFavouriteQuotes: GetFavouriteQuotes,
    val isQuoteFavourited: IsQuoteFavourited,
)
