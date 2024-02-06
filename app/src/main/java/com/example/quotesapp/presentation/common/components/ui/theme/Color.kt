package com.example.quotesapp.presentation.common.components.ui.theme

import androidx.compose.ui.graphics.Color

val Purple80 = Color(0xFFD0BCFF)
val PurpleGrey80 = Color(0xFFCCC2DC)
val Pink80 = Color(0xFFEFB8C8)

val Purple40 = Color(0xFF6650a4)
val PurpleGrey40 = Color(0xFF625b71)
val Pink40 = Color(0xFF7D5260)

val MidnightBlue = Color(0xFF1D212E)
val DeepNavyBlue = Color(0xFF25273A)
val CharcoalBlue = Color(0xFF171A24)
val LightNightBlue = Color(0xFF252D44)
val NeonGreen = Color(0xFF9FF6AD)
val GreyWhite = Color(0xFFAEAEAE)
val LightGrey = Color(0xFFA9A9A9)
val SearchGrey = Color(0xFF454A5A)
val BombGrey = Color(0xFFC1C1C1)
val BossyBlue = Color(0xFFB0CFFF)


//Tag Colors

val TaggyPurple = Color(0xFFA5118D)
val TaggyPeach = Color(0xFFCF6666)
val TaggyDarkBlue = Color(0xFF2C47A6)
val TaggyLightBlue = Color(0xFF368496)
val TaggyBrightGreen = Color(0xFF71B466)
val TaggyOrange = Color(0xFFBA7F0E)
val TaggyForest  = Color(0xFF215555)
val TaggyViolet = Color(0xFF92379A)
val TaggyGrass = Color(0xFF85A32E)
val TaggyRed = Color(0xFF7D0715)
val TaggyBrown = Color(0xFF602E00)
val TaggyCyan = Color(0xFF007964)




//Search Card

val SearchyBlueGrey = Color(0xFF2C3246)
val SearchyDarkBlue = Color(0xFF212738)
val SearchyLightBlue = Color(0xFF343C54)
val SearchyDarkGreen = Color(0xFF9FF6AD)
val TrixyGrey = Color(0xFFA3A3A3)




object TagColor {
    val ind = 0
    val colorList: List<Color> = listOf(
        TaggyPurple,
        TaggyPeach,
        TaggyDarkBlue,
        TaggyLightBlue,
        TaggyBrightGreen,
        TaggyOrange,
        TaggyForest,
        TaggyViolet,
        TaggyGrass,
        Color.DarkGray,
        TaggyRed,
        TaggyBrown,
        TaggyCyan
    )
}

fun TagColor.getRandom(num: Int):List<Color>{
    val list = emptyList<Color>()
    for(i in 0..num){
        list + colorList.random()
    }
    return list
}