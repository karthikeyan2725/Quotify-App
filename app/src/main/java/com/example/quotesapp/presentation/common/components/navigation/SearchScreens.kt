package com.example.quotesapp.presentation.common.components.navigation

const val RESULT_ARGUMENT_KEY = "tag"

sealed class SearchScreens(
    val route: String,
){
    data object SearchScreen: SearchScreens("search")
    data object ResultScreen: SearchScreens("result/{$RESULT_ARGUMENT_KEY}"){
        fun appendTag(tag:String): String{
            return "result/$tag"
        }
    }
}
