package com.example.quotesapp.presentation.common.components.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomBarScreen(
    val route: String,
    val title:String,
    val icon: ImageVector
) {
    data object HomeScreen : BottomBarScreen("Home","Home", Icons.Default.Home)
    data object FavouriteScreen: BottomBarScreen("Favourite","Favourite",Icons.Filled.Favorite)
    data object SearchScreen : BottomBarScreen("Search_Route","Search",Icons.Default.Search)
    data object ProfileScreen : BottomBarScreen("Profile","Profile",Icons.Default.Person)
}

