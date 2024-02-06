package com.example.quotesapp.data.data_source.remote

import com.example.quotesapp.data.data_source.remote.dto.QuoteDto
import com.example.quotesapp.data.data_source.remote.dto.QuotesResponseDto
import com.example.quotesapp.data.data_source.remote.dto.TagDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface QuotableApi {

    @GET("quotes/random")
    suspend fun getRandomQuotes(@Query("limit") limit: Int,@Query("maxLength") maxLength:Int): List<QuoteDto>

    @GET("tags")
    suspend fun getTags() : List<TagDto>

    @GET("search/quotes")
    suspend fun searchQuotes(@Query("query") search:String) : QuotesResponseDto

    @GET("quotes")
    suspend fun getQuotesWithTag(@Query("tags") tag : String) : QuotesResponseDto

    @GET("quotes/{id}")
    suspend fun getQuoteWithId(@Path("id") quoteId:String) : QuoteDto

}