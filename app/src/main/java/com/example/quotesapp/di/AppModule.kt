package com.example.quotesapp.di

import android.app.Application
import androidx.room.Room
import com.example.quotesapp.common.Constants
import com.example.quotesapp.data.data_source.local.FavouritesDatabase
import com.example.quotesapp.data.data_source.remote.QuotableApi
import com.example.quotesapp.data.repository.FavouriteRepositoryImpl
import com.example.quotesapp.data.repository.QuoteRepositoryImpl
import com.example.quotesapp.domain.repository.FavouriteRepository
import com.example.quotesapp.domain.repository.QuoteRepository
import com.example.quotesapp.domain.usecase.FavouritesUseCases
import com.example.quotesapp.domain.usecase.GetQuotesFavouritedFromApi
import com.example.quotesapp.domain.usecase.QuotesUseCases
import com.example.quotesapp.domain.usecase.all.AddFavouriteQuote
import com.example.quotesapp.domain.usecase.all.DeleteFavouriteQuote
import com.example.quotesapp.domain.usecase.all.GetFavouriteQuotes
import com.example.quotesapp.domain.usecase.all.GetQuoteWithTag
import com.example.quotesapp.domain.usecase.all.GetRandomQuotes
import com.example.quotesapp.domain.usecase.all.GetTags
import com.example.quotesapp.domain.usecase.all.IsQuoteFavourited
import com.example.quotesapp.domain.usecase.all.SearchQuotes
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideQuoteApi(): QuotableApi {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(Constants.QUOTABLE_BASE_URL)
            .build()
            .create<QuotableApi>()
    }


    @Provides
    @Singleton
    fun provideQuoteRepository(quotableApi: QuotableApi): QuoteRepository {
        return QuoteRepositoryImpl(quotableApi)
    }

    @Provides
    @Singleton
    fun provideFavouritesDatabase(app: Application): FavouritesDatabase {
        return Room.databaseBuilder(
            context = app.applicationContext,
            name = FavouritesDatabase.DATABASE_NAME,
            klass = FavouritesDatabase::class.java
        ).build()
    }


    @Provides
    @Singleton
    fun provideFavouriteRepository(favouritesDatabase: FavouritesDatabase): FavouriteRepository {
        return FavouriteRepositoryImpl(
            favouritesDatabase.favouriteDao()
        )
    }

    @Provides
    @Singleton
    fun provideQuotesUseCase(quoteRepository: QuoteRepository): QuotesUseCases {
        return QuotesUseCases(
            searchQuotes = SearchQuotes(quoteRepository),
            getTags = GetTags(quoteRepository),
            getRandomQuotes = GetRandomQuotes(quoteRepository),
            getQuoteWithTag = GetQuoteWithTag(quoteRepository)
        )
    }

    @Provides
    @Singleton
    fun provideFavouriteFromApi(quoteRepository: QuoteRepository,favouriteRepository: FavouriteRepository): GetQuotesFavouritedFromApi {
        return GetQuotesFavouritedFromApi(quoteRepository,favouriteRepository)
    }

    @Provides
    @Singleton
    fun provideFavouriteUseCase(favouriteRepository: FavouriteRepository): FavouritesUseCases {
        return FavouritesUseCases(
            addFavouriteQuote = AddFavouriteQuote(favouriteRepository),
            getFavouriteQuotes = GetFavouriteQuotes(favouriteRepository),
            deleteFavouriteQuote = DeleteFavouriteQuote(favouriteRepository),
            isQuoteFavourited = IsQuoteFavourited(favouriteRepository)
        )
    }
}