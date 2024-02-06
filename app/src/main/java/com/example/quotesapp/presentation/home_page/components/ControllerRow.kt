package com.example.quotesapp.presentation.home_page.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.quotesapp.presentation.common.components.ui.theme.LightGrey
import com.example.quotesapp.presentation.common.components.ui.theme.NeonGreen

@Composable
fun ControllerRow (
    isFavourite:Boolean,
    onLeftClick:()->Unit,
    onRightClick:()->Unit,
    onHeartClick:(Boolean)->Unit,
    onRefreshClick:()->Unit,
    onShareClick:()->Unit,
    modifier:Modifier = Modifier
){

    val arrowModifier = Modifier
        .size(100.dp)
    val centralIconModifier = Modifier
        .size(30.dp)
    val heartIcon = Modifier
        .padding(horizontal = 30.dp, vertical = 0.dp)
        .size(50.dp)


    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.Top,
        modifier = modifier
            .fillMaxWidth()
    ){
        Icon(
            Icons.Default.KeyboardArrowLeft,
            tint = LightGrey,
            contentDescription = "",
            modifier = arrowModifier
                .clickable {onLeftClick()}
        )

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
            ) {
                Icon(
                    Icons.Default.Refresh,
                    tint = Color.White,
                    contentDescription = null,
                    modifier = centralIconModifier
                        .clickable { onRefreshClick() }
                )
                Icon(
                    if(isFavourite) Icons.Filled.Favorite else Icons.Default.FavoriteBorder,
                    tint = NeonGreen,
                    contentDescription = null,
                    modifier = heartIcon
                        .clickable { onHeartClick(isFavourite) }
                )
                Icon(
                    Icons.Default.Share,
                    tint = Color.White,
                    contentDescription = null,
                    modifier = centralIconModifier
                        .clickable {  onShareClick()}
                )
            }
        }
        Icon(
            Icons.Default.KeyboardArrowRight,
            tint = LightGrey,
            contentDescription = "",
            modifier = arrowModifier
                .clickable { onRightClick() }
        )
    }
}