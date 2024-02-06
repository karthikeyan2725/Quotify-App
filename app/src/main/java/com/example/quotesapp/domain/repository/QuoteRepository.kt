package com.example.quotesapp.domain.repository

import com.example.quotesapp.data.data_source.remote.dto.QuoteDto
import com.example.quotesapp.data.data_source.remote.dto.QuotesResponseDto
import com.example.quotesapp.data.data_source.remote.dto.TagDto

interface QuoteRepository {

    suspend fun getRandomQuotes(limit:Int,maxLength:Int): List<QuoteDto>

    suspend fun getTags(): List<TagDto>

    suspend fun getQuotesWithTag(tag: String) : QuotesResponseDto

    suspend fun searchQuotes(search: String): QuotesResponseDto

    suspend fun getQuoteWithId(quoteId : String) : QuoteDto
}