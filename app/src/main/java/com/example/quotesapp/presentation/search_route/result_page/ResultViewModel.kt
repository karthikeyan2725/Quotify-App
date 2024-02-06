package com.example.quotesapp.presentation.search_route.result_page

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.quotesapp.common.Resource
import com.example.quotesapp.common.sendTextToWhatsapp
import com.example.quotesapp.di.Tag
import com.example.quotesapp.domain.model.Favourite
import com.example.quotesapp.domain.usecase.FavouritesUseCases
import com.example.quotesapp.domain.usecase.QuotesUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ResultViewModel @Inject constructor(
    @Tag val tag : String,
    private val favouritesUseCases: FavouritesUseCases,
    private val quotesUseCases: QuotesUseCases
): ViewModel() {

    private val _resultState = MutableStateFlow(ResultState())
    val resultState = _resultState.asStateFlow()
    private val _favouriteState = MutableStateFlow(emptyList<Favourite>())
    val favouriteState = _favouriteState.asStateFlow()

    init{
        getResults(tag)
        getFavourites()
    }

    private fun getResults(tag:String){
        quotesUseCases.getQuoteWithTag(tag).onEach {result->
            when(result){
                is Resource.Success ->{
                    _resultState.value = ResultState(quotes = result.data ?: emptyList())
                }
                is Resource.Error->{
                    _resultState.value = ResultState(error = result.message?: "An unexpected error Occurred")
                }
                is Resource.Loading ->{
                    _resultState.value = ResultState(loading = true)
                }
            }
        }.launchIn(viewModelScope)
    }

    fun onEvent(event: ResultPageEvent){
        when(event){
            is ResultPageEvent.AddToFavourite -> {
                viewModelScope.launch(Dispatchers.IO){
                    favouritesUseCases.addFavouriteQuote(event.favourite)
                }
            }
            is ResultPageEvent.Navigate -> {}
            is ResultPageEvent.RemoveFromFavourite -> {
                viewModelScope.launch(Dispatchers.IO){
                    favouritesUseCases.deleteFavouriteQuote(event.favourite)
                }
            }
            is ResultPageEvent.ShareQuote -> {
                sendTextToWhatsapp( event.quote ,context = event.context)
            }
        }
    }

    private fun getFavourites(){
        favouritesUseCases.getFavouriteQuotes().onEach {
            _favouriteState.value = it
        }.launchIn(viewModelScope)
    }
}