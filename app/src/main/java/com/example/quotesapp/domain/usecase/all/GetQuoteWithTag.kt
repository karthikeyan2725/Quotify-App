package com.example.quotesapp.domain.usecase.all

import com.example.quotesapp.common.Resource
import com.example.quotesapp.data.data_source.remote.dto.toQuote
import com.example.quotesapp.domain.model.Quote
import com.example.quotesapp.domain.repository.QuoteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetQuoteWithTag @Inject constructor(
    private val quoteRepository: QuoteRepository
) {
    operator fun invoke(tag:String): Flow<Resource<List<Quote>>> = flow{
         try{
             emit(Resource.Loading())
             val result = quoteRepository.getQuotesWithTag(tag)
             emit(Resource.Success(result.results.map{it.toQuote()}))
        }catch (e: HttpException){
            emit(Resource.Error(e.localizedMessage ?: "An HTTP error occurred"))
        }catch (e: IOException){
            emit(Resource.Error(e.localizedMessage?:"An unexpected error occurred"))
        }
    }
}