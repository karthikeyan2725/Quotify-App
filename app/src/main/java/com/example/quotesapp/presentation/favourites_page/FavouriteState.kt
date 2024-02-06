package com.example.quotesapp.presentation.favourites_page

import com.example.quotesapp.domain.model.Quote

data class FavouriteState(
    val quotes: List<Quote> = emptyList(),
    val loading: Boolean = false,
    val error: String = ""
)