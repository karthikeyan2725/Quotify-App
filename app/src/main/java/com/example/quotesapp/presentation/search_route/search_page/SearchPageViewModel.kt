package com.example.quotesapp.presentation.search_route.search_page

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.quotesapp.common.Resource
import com.example.quotesapp.common.sendTextToWhatsapp
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
class SearchPageViewModel @Inject constructor(
    private val quotesUsecases : QuotesUseCases,
    private val favouritesUseCases: FavouritesUseCases,
): ViewModel() {
    private val _tagState = MutableStateFlow(TagsState())
    val tagState = _tagState.asStateFlow()

    private val _searchState = MutableStateFlow(SearchState())
    val searchState = _searchState.asStateFlow()

    private val _favouriteState = MutableStateFlow(emptyList<Favourite>())
    val favouriteState = _favouriteState.asStateFlow()

    init {
        getTags()
        getFavourites()
    }

    private fun getFavourites(){
        favouritesUseCases.getFavouriteQuotes().onEach {
            _favouriteState.value = it
        }.launchIn(viewModelScope)
    }

    fun onEvent(event: FavouritePageEvent){
        when(event){
            is FavouritePageEvent.AddToFavourite -> {
                viewModelScope.launch(Dispatchers.IO){
                    favouritesUseCases.addFavouriteQuote(event.favourite)
                }
            }
            is FavouritePageEvent.Navigate -> {}
            is FavouritePageEvent.RemoveFromFavourite -> {
                viewModelScope.launch(Dispatchers.IO){
                    favouritesUseCases.deleteFavouriteQuote(event.favourite)
                }
            }
            is FavouritePageEvent.SearchQuote -> {
                searchQuote(event.search)
            }
            is FavouritePageEvent.ShareQuote -> {
                sendTextToWhatsapp( event.quote ,context = event.context)
            }
        }

    }

    private fun searchQuote(search: String){
        quotesUsecases.searchQuotes(search)
            .onEach {result->
            when(result){
                is Resource.Success->{
                    _searchState.value = SearchState(result.data?: emptyList())
                }
                is Resource.Loading->{
                    _searchState.value = SearchState(loading = true)
                }
                is Resource.Error->{
                    _searchState.value = SearchState(error = result.message ?: "Unexpected Error Occurred")
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun getTags(){
        quotesUsecases.getTags()
            .onEach {result->
                when(result){
                    is Resource.Success->{
                        _tagState.value = TagsState(result.data?: emptyList())
                    }
                    is Resource.Loading->{
                        _tagState.value = TagsState(isLoading = true)
                    }
                    is Resource.Error->{
                        _tagState.value = TagsState(errorString = result.message ?: "Unexpected Error Occurred")
                    }
                }
            }.launchIn(viewModelScope)
    }
}

