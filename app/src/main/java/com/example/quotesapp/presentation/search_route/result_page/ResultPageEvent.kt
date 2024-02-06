package com.example.quotesapp.presentation.search_route.result_page

import android.content.Context
import com.example.quotesapp.domain.model.Favourite
import com.example.quotesapp.domain.model.Quote
import com.example.quotesapp.presentation.common.components.navigation.NavigationEvent

sealed class ResultPageEvent {
    data class AddToFavourite(val favourite: Favourite): ResultPageEvent()
    data class RemoveFromFavourite(val favourite: Favourite): ResultPageEvent()
    data class ShareQuote(val quote:Quote,val context:Context): ResultPageEvent()
    data class Navigate(val navigationEvent: NavigationEvent) : ResultPageEvent()
}