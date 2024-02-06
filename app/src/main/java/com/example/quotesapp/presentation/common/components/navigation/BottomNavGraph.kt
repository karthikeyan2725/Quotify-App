package com.example.quotesapp.presentation.common.components.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.example.quotesapp.presentation.favourites_page.components.FavouritePage
import com.example.quotesapp.presentation.home_page.components.HomePage
import com.example.quotesapp.presentation.search_route.result_page.components.ResultPage
import com.example.quotesapp.presentation.search_route.search_page.components.SearchPage

@Composable
fun BottomNavGraph(navController: NavHostController) {
    NavHost(navController = navController,
        startDestination = BottomBarScreen.HomeScreen.route){

        composable(route= BottomBarScreen.HomeScreen.route){
            HomePage(navController)
        }

        composable(route= BottomBarScreen.FavouriteScreen.route){
            FavouritePage(navController)
        }

        navigation(
            route = BottomBarScreen.SearchScreen.route,
            startDestination = SearchScreens.SearchScreen.route
        ){
            composable(route = SearchScreens.SearchScreen.route){
                SearchPage(navController = navController)
            }
            composable(
                route = SearchScreens.ResultScreen.route,
                arguments = listOf(navArgument("tag"){
                    type = NavType.StringType
                })
            ){
                ResultPage(navController = navController)
            }
        }

    }
}

