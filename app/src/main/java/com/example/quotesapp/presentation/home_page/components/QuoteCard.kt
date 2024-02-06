package com.example.quotesapp.presentation.home_page.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.quotesapp.R
import com.example.quotesapp.domain.model.Quote
import com.example.quotesapp.presentation.common.components.shimmerEffect
import com.example.quotesapp.presentation.common.components.getCustomisedQuote

@Composable
fun QuoteCard(
    isLoading : Boolean,
    quote: Quote?,
    modifier : Modifier = Modifier,
    cardColor: Color = Color.Gray,
) {
    val quoteCardCol = Color.Gray
    if (isLoading) {
        Box(
            modifier = modifier
                .padding(4.dp)
                .clip(RoundedCornerShape(10.dp))
                .background(cardColor)
                .shimmerEffect(cardColor)
                .fillMaxSize()
        )
    } else {
        Box(
            modifier = modifier
                .padding(4.dp)
                .clip(RoundedCornerShape(10.dp))
                .paint(
                    painter = painterResource(id = R.drawable.ic_launcher_background),
                    contentScale = ContentScale.FillBounds
                )
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(30.dp)
                    .clip(RoundedCornerShape(20.dp))
                    .background(Color(0x50000000))
            ) {


                Text(
                    text = getCustomisedQuote(quote?.content?:"Quote"),
                    textAlign = TextAlign.Left,
                    lineHeight = 36.sp,
                    modifier = Modifier
                        .padding(20.dp)
                )


                Row(
                    horizontalArrangement = Arrangement.End,
                    modifier = modifier
                        .fillMaxWidth()
                ) {
                    Box(
                        modifier = Modifier
                            .padding(horizontal = 8.dp)
                            .clip(RoundedCornerShape(4.dp))
                            .background(Color(0x50000000))
                    ) {
                        Text(
                            text = "- ${quote?.author}",
                            color = Color.White,
                            fontStyle = FontStyle.Italic,
                            modifier = Modifier
                                .padding(4.dp)
                        )
                    }
                }
            }
        }
    }
}

