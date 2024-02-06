package com.example.quotesapp.presentation.search_route.search_page

import android.content.Context
import com.example.quotesapp.domain.model.Favourite
import com.example.quotesapp.domain.model.Quote
import com.example.quotesapp.presentation.common.components.navigation.NavigationEvent

sealed class FavouritePageEvent {
    data class AddToFavourite(val favourite: Favourite): FavouritePageEvent()
    data class RemoveFromFavourite(val favourite: Favourite): FavouritePageEvent()
    data class SearchQuote(val search: String): FavouritePageEvent()
    data class ShareQuote(val quote: Quote, val context: Context) : FavouritePageEvent()
    data class Navigate(val navigationEvent: NavigationEvent) : FavouritePageEvent()
}