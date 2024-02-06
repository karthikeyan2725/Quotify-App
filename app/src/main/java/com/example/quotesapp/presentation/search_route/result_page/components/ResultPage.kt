package com.example.quotesapp.presentation.search_route.result_page.components

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.quotesapp.domain.model.Favourite
import com.example.quotesapp.presentation.common.components.QuoteCard
import com.example.quotesapp.presentation.search_route.result_page.ResultPageEvent
import com.example.quotesapp.presentation.search_route.result_page.ResultViewModel
import java.time.LocalDateTime
import java.time.ZoneOffset

@Composable
fun ResultPage(
    navController: NavController,
    viewModel: ResultViewModel = hiltViewModel<ResultViewModel>()
){

    val state = viewModel.resultState.collectAsState()
    val favourites = viewModel.favouriteState.collectAsState()
    val context = LocalContext.current
    Log.d("RESULT PAGE",favourites.toString())

    LazyVerticalGrid(
        columns = GridCells.Fixed(1),
        contentPadding = PaddingValues(10.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ){
        items(state.value.quotes){quote->
            QuoteCard(
                quote,
                isFavourite = favourites.value.any { it.quoteId == quote.id  },
                onHeartClick = {isFavourite->
                               if(isFavourite){
                                   Log.d("RESULT PAGE","Remove")
                                   viewModel.onEvent(
                                       ResultPageEvent.RemoveFromFavourite(Favourite(quote.id))
                                   )
                               } else{
                                   Log.d("RESULT PAGE","Add")

                                   viewModel.onEvent(
                                       ResultPageEvent.AddToFavourite(
                                       Favourite(
                                           quote.id,
                                           LocalDateTime.now().toEpochSecond(ZoneOffset.UTC)
                                       )
                                   ))
                               }

                },
                onShareClick = {viewModel.onEvent(ResultPageEvent.ShareQuote(quote,context))},
                modifier = Modifier.height(IntrinsicSize.Max)
            )
        }
    }
}