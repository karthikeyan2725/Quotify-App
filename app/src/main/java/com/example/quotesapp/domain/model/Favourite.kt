package com.example.quotesapp.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Favourite(
    @PrimaryKey val quoteId: String,
    val favoriteTimeStamp: Long = 0
)
