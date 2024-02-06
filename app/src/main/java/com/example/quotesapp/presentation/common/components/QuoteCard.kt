package com.example.quotesapp.presentation.common.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.quotesapp.domain.model.Quote
import com.example.quotesapp.presentation.common.components.ui.theme.NeonGreen
import com.example.quotesapp.presentation.common.components.ui.theme.SearchGrey
import com.example.quotesapp.presentation.common.components.ui.theme.SearchyBlueGrey
import com.example.quotesapp.presentation.common.components.ui.theme.SearchyDarkBlue
import com.example.quotesapp.presentation.common.components.ui.theme.SearchyLightBlue
import com.example.quotesapp.presentation.common.components.ui.theme.inter_Family

@Composable
fun QuoteCard(
    quote: Quote,
    onHeartClick: (Boolean) -> Unit,
    onShareClick: () -> Unit,
    modifier: Modifier = Modifier,
    isFavourite: Boolean
) {

    val iconModifier = Modifier.padding(4.dp)

    Box(
        modifier = modifier.background(SearchyBlueGrey)
    ) {
        Row(
            modifier = Modifier
        ) {
            Column(
                modifier = Modifier.padding(8.dp).weight(6f)
            ) {
                Text(
                    "\"${quote.content}\"",
                    style = TextStyle(
                        fontFamily = inter_Family,
                        fontWeight = FontWeight.SemiBold,
                        color = Color.White,
                        letterSpacing = 1.sp
                    )
                )
                Row(
                    horizontalArrangement = Arrangement.End,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Box(
                        modifier = Modifier
                            .padding(10.dp)
                            .clip(RoundedCornerShape(8.dp))
                            .background(SearchyLightBlue)
                    ) {
                        Text(
                            "-${quote.author}",
                            Modifier.padding(4.dp),
                            style = TextStyle(
                                color = Color.White,
                                fontSize = 10.sp,
                                letterSpacing = 1.sp,
                                fontStyle = FontStyle.Italic,
                                fontWeight = FontWeight.Light
                            )
                        )
                    }
                }
            }
            Column(
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
                    .background(SearchyDarkBlue)
            ) {
                Icon(
                    if(isFavourite)Icons.Default.Favorite else Icons.Filled.FavoriteBorder,
                    tint = NeonGreen,
                    contentDescription = null,
                    modifier = iconModifier.clickable { onHeartClick(isFavourite) }
                )
                Icon(
                    Icons.Default.Share,
                    tint = SearchGrey,
                    contentDescription = null,
                    modifier = iconModifier.clickable { onShareClick() }
                )
            }
        }
    }
}

@Preview
@Composable
fun PreviewQuoteCard(){
    val testQuote = Quote(
        id = "lol",
        author = "Maurice J Bach",
        content = "Unix Internals is the most useless Subject for someone who develops android app.",
        length = 0,
        tags = listOf())
    QuoteCard(
        testQuote,
        {},
        {},
        isFavourite = true
    )
}