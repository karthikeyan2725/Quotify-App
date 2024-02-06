package com.example.quotesapp.presentation.favourites_page.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.quotesapp.domain.model.Favourite
import com.example.quotesapp.presentation.common.components.QuoteCard
import com.example.quotesapp.presentation.common.components.SearchLabel
import com.example.quotesapp.presentation.favourites_page.FavouritePageEvent
import com.example.quotesapp.presentation.favourites_page.FavouritePageViewModel
import java.time.LocalDateTime
import java.time.ZoneOffset

@Composable
fun FavouritePage(
    navController: NavController,
    viewModel : FavouritePageViewModel = hiltViewModel<FavouritePageViewModel>()
) {

    val favourites = viewModel.state.collectAsState()
    val context = LocalContext.current
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        if(favourites.value.loading){
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(10.dp)
            ){
                SearchLabel(string = "LOADING")
            }
        }
        else {
            LazyVerticalGrid(
                columns = GridCells.Fixed(1),
                contentPadding = PaddingValues(10.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                items(favourites.value.quotes) { quote ->
                    QuoteCard(
                        quote,
                        isFavourite = favourites.value.quotes.any { it == quote },
                        onHeartClick = { isFavourite ->
                            if (isFavourite) {
                                viewModel.onEvent(
                                    FavouritePageEvent.RemoveFromFavourite(
                                        Favourite(quoteId = quote.id)
                                    )
                                )
                            } else {
                                viewModel.onEvent(
                                    FavouritePageEvent.AddToFavourite(
                                        Favourite(
                                            favoriteTimeStamp = LocalDateTime.now().toEpochSecond(ZoneOffset.UTC),
                                            quoteId = quote.id
                                        )
                                    )
                                )
                            }

                        },
                        onShareClick = {viewModel.onEvent(FavouritePageEvent.ShareQuote(quote,context))},
                        modifier = Modifier.height(IntrinsicSize.Max)
                    )
                }
            }
        }
    }
}





