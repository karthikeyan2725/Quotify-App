package com.example.quotesapp.presentation.search_route.search_page.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.quotesapp.domain.model.Tag
import com.example.quotesapp.presentation.common.components.ui.theme.inter_Family

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TagCard(
    tag: Tag,
    color: Color,
    onClick: (Tag)->Unit,
    modifier : Modifier = Modifier
){
    Card(
        onClick = {onClick(tag)},
        colors = CardDefaults.cardColors(
            containerColor = color,
            contentColor = Color.White,
            disabledContainerColor = color,
            disabledContentColor = Color.Gray
        ),
    ){
        Text(
            tag.name,
            style = TextStyle(fontFamily = inter_Family, fontSize = 20.sp, fontWeight = FontWeight.Black, textAlign = TextAlign.Center),
            modifier = modifier.fillMaxWidth()
        )
    }
}

@Preview
@Composable
fun PreviewTagCard(){
    TagCard(
        tag = Tag(id = "",name="Motivation", quoteCount = 1),
        onClick = {},
        color = Color.Green)
}