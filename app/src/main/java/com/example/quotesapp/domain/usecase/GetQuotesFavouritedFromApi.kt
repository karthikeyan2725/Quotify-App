package com.example.quotesapp.domain.usecase

import com.example.quotesapp.common.Resource
import com.example.quotesapp.data.data_source.remote.dto.toQuote
import com.example.quotesapp.domain.model.Quote
import com.example.quotesapp.domain.repository.FavouriteRepository
import com.example.quotesapp.domain.repository.QuoteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetQuotesFavouritedFromApi @Inject constructor(
    private val quoteRepository: QuoteRepository,
    private val favouriteRepository: FavouriteRepository
) {
    operator fun invoke(): Flow<Resource<List<Quote>>> = flow {
        try{
            emit(Resource.Loading())
            favouriteRepository.getFavourites()
                .map { favouriteList ->
                    favouriteList.map{
                        quoteRepository.getQuoteWithId(it.quoteId).toQuote()
                    }
                }.collect{result->
                    emit(Resource.Success(result))
                }
        } catch (e:HttpException){
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred"))
        } catch (e: IOException){
            emit(Resource.Error(e.localizedMessage?:"An unexpected error occurred"))
        }
    }
}