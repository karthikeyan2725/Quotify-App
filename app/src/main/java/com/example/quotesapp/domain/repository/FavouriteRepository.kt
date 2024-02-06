package com.example.quotesapp.domain.repository

import com.example.quotesapp.domain.model.Favourite
import kotlinx.coroutines.flow.Flow

interface FavouriteRepository {
    fun getFavourites(): Flow<List<Favourite>>

    suspend fun upsertFavourite(favourite: Favourite)

    suspend fun deleteFavourite(favourite: Favourite)

    suspend fun getFavouriteWithId(quoteId: String) : Favourite?
}