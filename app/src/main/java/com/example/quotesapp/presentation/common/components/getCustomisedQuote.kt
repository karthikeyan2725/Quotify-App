package com.example.quotesapp.presentation.common.components

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.quotesapp.presentation.common.components.ui.theme.inter_Family
import java.util.Locale

fun getCustomisedQuote(
    quote : String
) : AnnotatedString{
    val string = quote.uppercase(Locale.ENGLISH).replace(".","")
    return buildAnnotatedString {

        pushStyle(SpanStyle(
            color = Color.Yellow,
            fontFamily = inter_Family,
            fontSize = 40.sp,
            fontWeight = FontWeight.Bold,
        ))
        append("\" ")

        pushStyle(
            SpanStyle(
                color = Color.White,
                fontWeight = FontWeight.SemiBold,
                fontFamily = inter_Family,
                fontSize = 32.sp
            )
        )

        append(string)

        pushStyle(
            SpanStyle(
                color = Color.Yellow,
                fontFamily = inter_Family,
                fontWeight = FontWeight.Bold,
                fontSize = 40.sp
            )
        )

        append(" \"")
    }
}