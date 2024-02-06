package com.example.quotesapp.data.data_source.remote.dto

data class QuotesResponseDto(
    val count: Int,
    val lastItemIndex: Int,
    val page: Int,
    val results: List<QuoteDto>,
    val totalCount: Int,
    val totalPages: Int
)