package com.example.quotesapp.presentation.common.components

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.quotesapp.presentation.common.components.ui.theme.NeonGreen
import com.example.quotesapp.presentation.common.components.ui.theme.inter_Family

fun getAppTitle(): AnnotatedString {
    return buildAnnotatedString {
        pushStyle(
            SpanStyle(
                color = NeonGreen,
                fontSize = 30.sp,
                fontFamily = inter_Family,
                fontStyle = FontStyle.Italic,
                fontWeight = FontWeight.Bold
            )
        )
        append("Q")
        pop()
        pushStyle(
            SpanStyle(
                color = Color.White,
                fontSize = 20.sp,
                fontFamily = inter_Family,
                fontWeight = FontWeight.SemiBold
            )
        )
        append("uotify")
        toAnnotatedString()
    }
}

