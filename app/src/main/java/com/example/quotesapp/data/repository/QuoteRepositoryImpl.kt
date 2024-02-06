package com.example.quotesapp.data.repository

import com.example.quotesapp.data.data_source.remote.QuotableApi
import com.example.quotesapp.data.data_source.remote.dto.QuoteDto
import com.example.quotesapp.data.data_source.remote.dto.QuotesResponseDto
import com.example.quotesapp.data.data_source.remote.dto.TagDto
import com.example.quotesapp.domain.repository.QuoteRepository
import javax.inject.Inject

class QuoteRepositoryImpl @Inject constructor(
    private val quotableApi: QuotableApi,
): QuoteRepository{

    override suspend fun getRandomQuotes(limit: Int,maxLength:Int): List<QuoteDto>{
        return quotableApi.getRandomQuotes(limit,maxLength)
    }

    override suspend fun getTags(): List<TagDto> {
        return quotableApi.getTags()
    }

    override suspend fun getQuotesWithTag(tag: String): QuotesResponseDto {
        return quotableApi.getQuotesWithTag(tag)
    }

    override suspend fun searchQuotes(search: String): QuotesResponseDto {
        return quotableApi.searchQuotes(search)
    }

    override suspend fun getQuoteWithId(quoteId: String): QuoteDto {
        return quotableApi.getQuoteWithId(quoteId)
    }

}

