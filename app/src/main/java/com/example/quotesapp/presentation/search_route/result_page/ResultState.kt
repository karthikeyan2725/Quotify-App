package com.example.quotesapp.presentation.search_route.result_page

import com.example.quotesapp.domain.model.Quote

data class ResultState(
    val quotes : List<Quote> = emptyList(),
    val loading : Boolean = false,
    val error : String = ""
)