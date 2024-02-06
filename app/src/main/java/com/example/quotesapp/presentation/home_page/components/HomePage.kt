package com.example.quotesapp.presentation.home_page.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.quotesapp.presentation.common.components.ui.theme.NeonGreen
import com.example.quotesapp.presentation.common.components.ui.theme.inter_Family
import com.example.quotesapp.presentation.home_page.HomeEvent
import com.example.quotesapp.presentation.home_page.HomePageViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomePage(
    navController : NavHostController,
    viewModel:HomePageViewModel = hiltViewModel<HomePageViewModel>(),
    modifier: Modifier = Modifier
) {

    val state = viewModel.homeState.collectAsState()
    val favouriteState = viewModel.favouriteState.collectAsState()
    Column(
        modifier = modifier
            .fillMaxSize()
    ){

        Text(
            "Today's Quotes",
            color = Color.White,
            fontFamily = inter_Family,
            fontWeight = FontWeight.Normal,
            fontSize = 24.sp,
            modifier = Modifier
                .padding(vertical = 24.dp)
                .padding(start = 8.dp)
        )

        QuoteCard(
            state.value.loading,
            if(state.value.quotes.isNotEmpty()) state.value.quotes[state.value.currentQuote] else null,
            Modifier
                .padding(8.dp)
                .weight(3f),
            cardColor = NeonGreen
        )
        val localContext = LocalContext.current
        ControllerRow(
            isFavourite = favouriteState.value.any { it.quoteId ==  if(state.value.quotes.isNotEmpty()) state.value.quotes[state.value.currentQuote].id else "" },

            onHeartClick =
            {isFavourite->
                if(isFavourite){
                    viewModel.onEvent(HomeEvent.RemoveFromFavourite)
                }
                else{
                    viewModel.onEvent(HomeEvent.AddToFavourite)
                }
            },

            onLeftClick = {viewModel.onEvent(HomeEvent.NextQuote)},

            onRefreshClick = {viewModel.onEvent(HomeEvent.RefreshQuote)},

            onRightClick = {viewModel.onEvent(HomeEvent.PreviousQuote)},

            onShareClick = { viewModel.onEvent(HomeEvent.ShareQuote(localContext)) },

            modifier = Modifier
                .weight(1f)
                .padding(top = 10.dp)
        )
    }
}




