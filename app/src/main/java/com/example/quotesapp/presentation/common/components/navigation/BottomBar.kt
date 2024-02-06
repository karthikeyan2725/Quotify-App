package com.example.quotesapp.presentation.common.components.navigation

import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.quotesapp.presentation.common.components.ui.theme.CharcoalBlue

@Composable
fun BottomBar(navController : NavHostController){

    val screens = listOf(
        BottomBarScreen.HomeScreen,
        BottomBarScreen.SearchScreen,
        BottomBarScreen.FavouriteScreen,
        BottomBarScreen.ProfileScreen
    )

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    NavigationBar(
        contentColor = Color.White,
        containerColor = CharcoalBlue,
        tonalElevation = 10.dp
    ) {
        screens.forEach {
            AddBottomItem(
                screen = it,
                currentDestination = currentDestination,
                navController = navController
            )
        }
    }
}

@Composable
fun RowScope.AddBottomItem(
    screen : BottomBarScreen,
    currentDestination : NavDestination?,
    navController: NavHostController
){
   NavigationBarItem(
       label = {screen.title},
       icon = { Icon(screen.icon,contentDescription = null,Modifier.size(30.dp)) },
       onClick = {
           navController.navigate(screen.route){
               popUpTo(screen.route){inclusive=true}
           } },
       selected = currentDestination?.hierarchy?.any {
           it.route == screen.route
       } ?: false
   )
}