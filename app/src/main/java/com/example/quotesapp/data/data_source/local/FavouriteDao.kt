package com.example.quotesapp.data.data_source.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.example.quotesapp.domain.model.Favourite
import kotlinx.coroutines.flow.Flow

@Dao
interface FavouriteDao {

    @Upsert
    suspend fun upsertFavourite(favourite: Favourite)

    @Delete
    suspend fun deleteFavourite(favourite: Favourite)

    @Query("SELECT * from favourite where quoteId = :quoteId")
    suspend fun getFavouriteQuoteWithId(quoteId:String) : Favourite?

    @Query("SELECT * from favourite")
    fun getAllFavourites() : Flow<List<Favourite>>

}