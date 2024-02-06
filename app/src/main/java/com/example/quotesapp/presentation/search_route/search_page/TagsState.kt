package com.example.quotesapp.presentation.search_route.search_page

import com.example.quotesapp.domain.model.Tag

data class TagsState(
    val tags : List<Tag> = emptyList(),
    val isLoading : Boolean = false,
    val errorString: String = ""
)