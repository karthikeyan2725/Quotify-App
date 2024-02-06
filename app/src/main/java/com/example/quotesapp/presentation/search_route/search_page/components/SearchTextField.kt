package com.example.quotesapp.presentation.search_route.search_page.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.quotesapp.presentation.common.components.ui.theme.BombGrey
import com.example.quotesapp.presentation.common.components.ui.theme.SearchGrey

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchTextField(
    value: String,
    placeholder: String = "Search",
    onValueChange : (String) -> Unit
){
    val searchStyle = TextStyle(
        color = BombGrey,
        fontSize = 16.sp
    )

    TextField(
        value = value,
        shape = RectangleShape,
        colors = TextFieldDefaults.colors(
            focusedContainerColor = SearchGrey,
            unfocusedContainerColor = SearchGrey,
            disabledContainerColor = SearchGrey,
            cursorColor = BombGrey,
            unfocusedIndicatorColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent
        ),
        textStyle = searchStyle,
        onValueChange={ onValueChange(it) },
        placeholder = { Text(placeholder, style = searchStyle) },
        maxLines = 1,
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    )

}


@Preview
@Composable
fun PreviewSearchBar(){
    SearchTextField(value = "lol", onValueChange = {})
}