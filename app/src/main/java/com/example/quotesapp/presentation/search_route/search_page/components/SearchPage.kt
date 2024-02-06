package com.example.quotesapp.presentation.search_route.search_page.components

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.quotesapp.domain.model.Favourite
import com.example.quotesapp.presentation.common.components.QuoteCard
import com.example.quotesapp.presentation.common.components.SearchLabel
import com.example.quotesapp.presentation.common.components.navigation.SearchScreens
import com.example.quotesapp.presentation.common.components.ui.theme.TagColor
import com.example.quotesapp.presentation.search_route.search_page.FavouritePageEvent
import com.example.quotesapp.presentation.search_route.search_page.SearchPageViewModel
import java.time.LocalDateTime
import java.time.ZoneOffset

@Composable
fun SearchPage(
    navController: NavController,
    viewModel : SearchPageViewModel = hiltViewModel<SearchPageViewModel>()
) {
    val context = LocalContext.current
    val tagState = viewModel.tagState.collectAsState()
    var searchText by remember { mutableStateOf("") }
    val favourites = viewModel.favouriteState.collectAsState()
    val state = viewModel.searchState.collectAsState()
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        SearchTextField(value = searchText, onValueChange = {
            viewModel.onEvent(FavouritePageEvent.SearchQuote(it))
            searchText = it
        })
        if (searchText.isBlank()) {

            SearchLabel("CATEGORY")
            LazyVerticalStaggeredGrid(
                columns = StaggeredGridCells.Fixed(2),
                contentPadding = PaddingValues(20.dp),
                verticalItemSpacing = 20.dp,
                horizontalArrangement = Arrangement.spacedBy(20.dp),
            ) {
                itemsIndexed(tagState.value.tags) { ind,tag ->
                    TagCard(
                        tag = tag,
                        color = TagColor.colorList[ind%TagColor.colorList.size],
                        onClick = { navController.navigate(SearchScreens.ResultScreen.appendTag(tag.name)) },
                        modifier = Modifier.padding(horizontal = 2.dp, vertical = 30.dp)
                    )
                }
            }
        } else {
            if (state.value.loading) {
                SearchLabel("LOADING")
            } else {
                LazyVerticalGrid(
                    columns = GridCells.Fixed(1),
                    contentPadding = PaddingValues(10.dp),
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    items(state.value.quotes) { quote ->
                        QuoteCard(
                            quote,
                            isFavourite = favourites.value.any { it.quoteId == quote.id },
                            onHeartClick = { isFavourite ->
                                if (isFavourite) {
                                    Log.d("RESULT PAGE", "Remove")
                                    viewModel.onEvent(
                                        FavouritePageEvent.RemoveFromFavourite(Favourite(quote.id))
                                    )
                                } else {
                                    Log.d("RESULT PAGE", "Add")

                                    viewModel.onEvent(
                                        FavouritePageEvent.AddToFavourite(
                                            Favourite(
                                                quote.id,
                                                LocalDateTime.now().toEpochSecond(ZoneOffset.UTC)
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
}



