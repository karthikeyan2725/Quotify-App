package com.example.quotesapp.data.data_source.remote.dto

import com.example.quotesapp.domain.model.Quote
import com.google.gson.annotations.SerializedName

data class QuoteDto(
    @SerializedName("_id") val id: String,
    val author: String,
    val authorSlug: String,
    val content: String,
    val dateAdded: String,
    val dateModified: String,
    val length: Int,
    val tags: List<Any>
)

fun QuoteDto.toQuote(): Quote{
    return Quote(
        id = id,
        author = author,
        content = content,
        length = length,
        tags = tags
    )
}