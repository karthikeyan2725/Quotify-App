package com.example.quotesapp.presentation.favourites_page

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.quotesapp.common.Resource
import com.example.quotesapp.common.sendTextToWhatsapp
import com.example.quotesapp.domain.usecase.FavouritesUseCases
import com.example.quotesapp.domain.usecase.GetQuotesFavouritedFromApi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class FavouritePageViewModel @Inject constructor(
    private val favouritesUseCases: FavouritesUseCases,
    private val getQuotesFavouritedFromApi: GetQuotesFavouritedFromApi
) : ViewModel(){
    private val _state = MutableStateFlow(FavouriteState())
    val state = _state.asStateFlow()

    init{
        getFavourites()
    }

    private fun getFavourites(){
        getQuotesFavouritedFromApi().onEach{result->
            when(result){
                is Resource.Success->{
                    _state.value = FavouriteState(
                        quotes = result.data ?: emptyList()
                    )
                }
                is Resource.Loading->{
                    _state.value = FavouriteState(
                        loading = true
                    )
                }
                is Resource.Error->{
                    _state.value = FavouriteState(
                        error = result.message ?: "An unexpected error occurred"
                    )
                }
            }
        }.launchIn(viewModelScope)
    }


    fun onEvent(event: FavouritePageEvent){
        when(event){
            is FavouritePageEvent.AddToFavourite -> {
                viewModelScope.launch(Dispatchers.IO){
                    Log.d("Lucky","ADDED ${event.favourite}")
                    favouritesUseCases.addFavouriteQuote(event.favourite)
                }
            }
            is FavouritePageEvent.Navigate -> {
            }
            is FavouritePageEvent.RemoveFromFavourite -> {
                viewModelScope.launch(Dispatchers.IO) {
                    Log.d("Lucky","REMOVED ${event.favourite}")
                    favouritesUseCases.deleteFavouriteQuote(event.favourite)
                }
            }
            is FavouritePageEvent.ShareQuote -> {
                sendTextToWhatsapp( event.quote ,context = event.context)
            }
        }
    }
}