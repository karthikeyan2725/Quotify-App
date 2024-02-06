package com.example.quotesapp.presentation.common.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.quotesapp.presentation.common.components.ui.theme.BossyBlue
import com.example.quotesapp.presentation.common.components.ui.theme.inter_Family

@Composable
fun SearchLabel(string: String){
    Row(
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxWidth()
    ){
        Text(
            string,
            style = TextStyle(
                color = BossyBlue,
                fontFamily = inter_Family,
                fontWeight = FontWeight.Medium,
                letterSpacing = 4.sp
            )
        )
    }
}
