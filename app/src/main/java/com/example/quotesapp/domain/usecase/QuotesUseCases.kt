package com.example.quotesapp.domain.usecase

import com.example.quotesapp.domain.usecase.all.GetQuoteWithTag
import com.example.quotesapp.domain.usecase.all.GetRandomQuotes
import com.example.quotesapp.domain.usecase.all.GetTags
import com.example.quotesapp.domain.usecase.all.SearchQuotes

data class QuotesUseCases(
    val getRandomQuotes: GetRandomQuotes,
    val getTags: GetTags,
    val searchQuotes: SearchQuotes,
    val getQuoteWithTag: GetQuoteWithTag,)
