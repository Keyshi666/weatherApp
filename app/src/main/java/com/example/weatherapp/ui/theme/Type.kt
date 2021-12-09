package com.example.weatherapp.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.weatherapp.R

val fonts = FontFamily(
    Font(R.font.lato_regular),
    Font(R.font.lato_bold, weight = FontWeight.Bold),
    Font(R.font.lato_light, weight = FontWeight.Light)
)
// Set of Material typography styles to start with
val Typography = Typography(
    h1 = TextStyle(
        fontFamily = fonts,
        fontWeight = FontWeight.Light,
    ),

    h2 = TextStyle(
        fontFamily = fonts,
        fontWeight = FontWeight.Normal,
    ),

    body1 = TextStyle(
        fontFamily = fonts,
        fontWeight = FontWeight.Normal,
    ),

    body2 = TextStyle(
        fontFamily = fonts,
        fontWeight = FontWeight.Bold,
        fontSize = 18.sp
    ),

    caption = TextStyle(
        fontFamily = fonts,
        fontWeight = FontWeight.Bold,
        fontSize = 18.sp
    ),

    /* Other default text styles to override

    ),
    caption = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp
    )
    */
)