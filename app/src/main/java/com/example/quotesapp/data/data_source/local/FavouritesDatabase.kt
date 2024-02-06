package com.example.quotesapp.data.data_source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.quotesapp.domain.model.Favourite

@Database(entities = [Favourite::class], version = 1)
abstract class FavouritesDatabase : RoomDatabase(){
    companion object{
        const val DATABASE_NAME = "favourites_db"
    }
    abstract fun favouriteDao() : FavouriteDao
}