package com.example.quotesapp.presentation.search_route.search_page

import com.example.quotesapp.domain.model.Quote

data class SearchState(
    val quotes : List<Quote> = emptyList(),
    val loading : Boolean = false,
    val error : String = ""
)