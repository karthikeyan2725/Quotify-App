package com.example.quotesapp.domain.usecase.all

import com.example.quotesapp.common.Constants
import com.example.quotesapp.common.Resource
import com.example.quotesapp.data.data_source.remote.dto.toTag
import com.example.quotesapp.domain.model.Tag
import com.example.quotesapp.domain.repository.QuoteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetTags @Inject constructor(
    private val quoteRepository : QuoteRepository
) {

    operator fun invoke(): Flow<Resource<List<Tag>>> = flow {
        try{
            emit(Resource.Loading())
            val tags = quoteRepository.getTags().filter{it.quoteCount> Constants.MIN_QUOTE_COUNT}.map{it.toTag()}
            emit(Resource.Success(tags))
        }catch(e: HttpException){
            emit(Resource.Error(e.localizedMessage ?: "An HTTP error occurred"))
        }catch (e:IOException){
            emit(Resource.Error(e.localizedMessage?: "Couldn't connect with the server"))
        }
    }

}
