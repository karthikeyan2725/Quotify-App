package com.example.quotesapp.data.data_source.remote.dto

import com.example.quotesapp.domain.model.Tag
import com.google.gson.annotations.SerializedName

data class TagDto(
    @SerializedName("_id") val id: String,
    val dateAdded: String,
    val dateModified: String,
    val name: String,
    val quoteCount: Int,
    val slug: String
)

fun TagDto.toTag(): Tag {
    return Tag(
        id = id,
        name = name,
        quoteCount = quoteCount
    )
}