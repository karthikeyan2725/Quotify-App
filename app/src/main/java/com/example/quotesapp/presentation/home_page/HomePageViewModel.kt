package com.example.quotesapp.presentation.home_page


import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.quotesapp.common.Constants
import com.example.quotesapp.common.Resource
import com.example.quotesapp.common.sendTextToWhatsapp
import com.example.quotesapp.domain.model.Favourite
import com.example.quotesapp.domain.usecase.FavouritesUseCases
import com.example.quotesapp.domain.usecase.QuotesUseCases
import com.example.quotesapp.presentation.common.util.nextCircularIndex
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.time.ZoneOffset
import javax.inject.Inject

@HiltViewModel
class HomePageViewModel @Inject constructor(
    private val quotesUseCases: QuotesUseCases,
    private val favouritesUseCases: FavouritesUseCases,
    private val application: Application
) : ViewModel(){

    private val _homeState = MutableStateFlow(HomePageState())
    val homeState = _homeState.asStateFlow()
    private val _favouriteState = MutableStateFlow(emptyList<Favourite>())
    val favouriteState = _favouriteState.asStateFlow()

    init{
        getRandomQuotes()
        getFavourites()
    }

    fun onEvent(event: HomeEvent){
        when(event){
            is HomeEvent.AddToFavourite -> {
                viewModelScope.launch (Dispatchers.IO){
                    favouritesUseCases.addFavouriteQuote(
                        Favourite(
                            favoriteTimeStamp = LocalDateTime.now().toEpochSecond(ZoneOffset.UTC),
                            quoteId = if(_homeState.value.quotes.isNotEmpty()) _homeState.value.quotes[_homeState.value.currentQuote].id else ""
                        )
                    )
                }
            }
            is HomeEvent.RemoveFromFavourite -> {
                viewModelScope.launch (Dispatchers.IO){
                    favouritesUseCases.deleteFavouriteQuote(
                        Favourite(quoteId = if(_homeState.value.quotes.isNotEmpty()) _homeState.value.quotes[_homeState.value.currentQuote].id else "")
                    )
                }
            }
            is HomeEvent.RefreshQuote -> {
                getRandomQuotes()
            }
            is HomeEvent.ShareQuote -> {
                val currQuote = if(_homeState.value.quotes.isNotEmpty()) _homeState.value.quotes[_homeState.value.currentQuote] else null
                if (currQuote != null) {
                    sendTextToWhatsapp( currQuote ,context = event.context)
                }
            }
            is HomeEvent.NextQuote -> {
                _homeState.update {currState->
                    currState.copy(
                        currentQuote =  currState.quotes.nextCircularIndex(currState.currentQuote,1) ?: 0
                    )
                }
            }
            is HomeEvent.PreviousQuote -> {
                _homeState.update {currState->
                    currState.copy(
                        currentQuote =  currState.quotes.nextCircularIndex(currState.currentQuote,-1) ?: 0
                    )
                }
            }
            is HomeEvent.Navigate -> {

            }
        }
    }

    private fun getRandomQuotes(){
        quotesUseCases
            .getRandomQuotes(Constants.NUM_QUOTES_TODAY,Constants.MAX_LENGTH_QUOTE)
            .onEach {result->
                when(result){
                    is Resource.Success->{
                        _homeState.value = HomePageState(
                            quotes = result.data ?: emptyList(),
                        )
                    }
                    is Resource.Loading->{
                        _homeState.value = HomePageState(
                            loading = true
                        )
                    }
                    is Resource.Error->{
                        _homeState.value = HomePageState(
                            error = result.message ?: "An unexpected error occurred"
                        )
                    }
                }
            }
            .launchIn(viewModelScope)
    }

    private fun getFavourites(){
        favouritesUseCases.getFavouriteQuotes().onEach {
            _favouriteState.value = it
        }.launchIn(viewModelScope)
    }
}