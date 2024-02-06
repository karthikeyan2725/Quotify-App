package com.example.quotesapp.domain.model

import com.google.gson.annotations.SerializedName

data class Tag(
    @SerializedName("_id") val id: String,
    val name: String,
    val quoteCount: Int
)