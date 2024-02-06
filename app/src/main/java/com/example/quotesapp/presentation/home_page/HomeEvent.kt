package com.example.quotesapp.presentation.home_page

import android.content.Context
import com.example.quotesapp.presentation.common.components.navigation.NavigationEvent

sealed class HomeEvent {
    data object AddToFavourite: HomeEvent()
    data object RemoveFromFavourite: HomeEvent()
    data object RefreshQuote : HomeEvent()
    data class ShareQuote(val context: Context) : HomeEvent()
    data object NextQuote: HomeEvent()
    data object PreviousQuote:  HomeEvent()
    data class Navigate(val navigationEvent: NavigationEvent) : HomeEvent()
}