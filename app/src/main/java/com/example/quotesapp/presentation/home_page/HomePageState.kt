package com.example.quotesapp.presentation.home_page

import androidx.compose.ui.graphics.Color
import com.example.quotesapp.domain.model.Quote

data class HomePageState(
    val quotes: List<Quote> = emptyList(),
    val colors: List<Color> = emptyList(),
    val backgroundUrl: List<String> = emptyList(),
    val currentQuote: Int = 0,
    val loading: Boolean = false,
    val error: String = ""
)
