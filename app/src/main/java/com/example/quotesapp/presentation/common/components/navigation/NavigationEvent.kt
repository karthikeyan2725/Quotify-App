package com.example.quotesapp.presentation.common.components.navigation

sealed class NavigationEvent {
    data object ToHome : NavigationEvent()
    data object ToSearch: NavigationEvent()
    data object ToFavourites: NavigationEvent()
    data object ToProfile: NavigationEvent()
}