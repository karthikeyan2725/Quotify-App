package com.example.quotesapp.domain.model

import com.google.gson.annotations.SerializedName

data class Quote (
    @SerializedName("_id") val id: String,
    val author: String,
    val content: String,
    val length: Int,
    val tags: List<Any>
)