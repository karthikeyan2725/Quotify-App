package com.example.quotesapp.data.repository

import com.example.quotesapp.data.data_source.local.FavouriteDao
import com.example.quotesapp.domain.model.Favourite
import com.example.quotesapp.domain.repository.FavouriteRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FavouriteRepositoryImpl @Inject constructor(
    private val favouriteDao: FavouriteDao
) : FavouriteRepository {

    override  fun getFavourites(): Flow<List<Favourite>> {
        return favouriteDao.getAllFavourites()
    }

    override suspend fun upsertFavourite(favourite: Favourite) {
        favouriteDao.upsertFavourite(favourite)
    }

    override suspend fun deleteFavourite(favourite: Favourite) {
        favouriteDao.deleteFavourite(favourite)
    }

    override suspend fun getFavouriteWithId(quoteId: String): Favourite? {
        return favouriteDao.getFavouriteQuoteWithId(quoteId)
    }

}