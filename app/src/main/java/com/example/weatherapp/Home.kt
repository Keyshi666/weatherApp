package com.example.weatherapp

import android.view.View
import android.widget.PopupWindow
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement.Absolute.Center
import androidx.compose.foundation.selection.toggleable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Popup
import androidx.compose.ui.window.isPopupLayout
import com.example.weatherapp.R
import com.example.weatherapp.ui.theme.WeatherAppTheme
import com.example.weatherapp.ui.theme.WhiteTransparentText


@Composable
fun Home(response : WeatherResponse) : String {
    val defCity = stringResource(R.string.city)
    val selectedCity : MutableState <String> = remember { mutableStateOf(defCity) }
    val isChangeCityClicked = remember{mutableStateOf(false)}
    WeatherAppTheme(darkTheme=false){
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(color = MaterialTheme.colors.background)
        )

            {
                if(isChangeCityClicked.value) {
                    ChooseCityDialog(isChangeCityClicked, selectedCity)
                }
                else {
                Column(
                    Modifier
                        .fillMaxWidth()
                        .padding(bottom = 5.dp)
                )

                {
                    Row(
                        Modifier
                            .padding(top = 15.dp, start = 15.dp, end = 35.dp)
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        text(selectedCity)
                        Row {
                            Text(
                                "º",
                                Modifier.padding(end = 7.dp),
                                fontSize = 18.sp,
                                color = MaterialTheme.colors.primaryVariant
                            )
                            CustomToggleGroup()
                        }
                    }


                    Row(
                        Modifier
                            .padding(start = 15.dp, end = 35.dp)
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        ChangeCity(isChangeCityClicked)
                        CreateToggleButton()
                    }
                }
            }
            Column(Modifier.padding(top=167.dp)){
                DisplayCurrentWeather("response.results[0].description!!", "-10º")
            }
            Row (
                Modifier
                    .fillMaxWidth()
                    .padding(top = 450.dp, start = 22.dp, end = 44.dp),
                horizontalArrangement = Arrangement.SpaceBetween) {
                Column() {
                    CreateWeatherProperty("Ветер", "response.results[0].wind_speed!!.toString()")
                    CreateWeatherProperty("Влажность", "60%")
                }
                Column () {
                    CreateWeatherProperty("Давление", "752 мм рт. ст.")
                    CreateWeatherProperty("Направение ветра", "западный")
                }
            }
        }


    }
    return selectedCity.toString()
}

@Composable
fun ChooseCityDialog(isChangeCityClicked: MutableState<Boolean>, selectedCity: MutableState<String>) {
    Row(Modifier
        .padding(start=21.dp, top=27.dp)
        .size(332.dp,53.dp)
        .background(color = MaterialTheme.colors.primary)
        .clip(shape = RoundedCornerShape(size = 4.dp)),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically) {
        OutlinedTextField(
            value = selectedCity.value,
            onValueChange = { selectedCity.value = it },
            textStyle = MaterialTheme.typography.body1
        )

                Text("OK",
                Modifier
                    .padding(top=3.dp, end=10.dp)
                    .clickable { isChangeCityClicked.value = false },
                softWrap = false,
                style = MaterialTheme.typography.body1,
                color = Color(0xFF1086FF),
                fontSize = 15.sp) }

    }

@Composable
fun DisplayCurrentWeather(weather: String, weatherDegrees: String){
    Column(
        Modifier
            .height(240.dp)
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row {
            Image(
                imageVector = ImageVector.vectorResource(R.drawable.ic_cloud),
                contentDescription = null
            )
            Text(
                text = weatherDegrees,
                softWrap = false,
                style = MaterialTheme.typography.h1,
                color = MaterialTheme.colors.primary,
                fontSize = 90.sp
            )
        }
        Text(text = weather,
            style = MaterialTheme.typography.body1,
            color = MaterialTheme.colors.primary,
            fontSize = 18.sp)
    }
}

@Composable
fun CreateWeatherProperty(key: String, value: String){
    Column(Modifier.padding(bottom = 25.dp)) {
        Text(
            text = key,
            style = MaterialTheme.typography.body1,
            color = MaterialTheme.colors.onSecondary,
            fontSize = 15.sp
        )
        Text(
            text = value,
            style = MaterialTheme.typography.body2,
            color = MaterialTheme.colors.primary,
            fontSize = 18.sp
        )
    }
}

@Composable
fun CreateToggleButton() {
    val checked = remember {
        mutableStateOf(false)
    }
    IconToggleButton(
        checked = checked.value,
        onCheckedChange = { checked.value = it },
        Modifier
            .width(185.dp)) {
        Row(horizontalArrangement = Arrangement.SpaceAround){
            Icon(painterResource(R.drawable.ic_location), "location", tint=MaterialTheme.colors.primaryVariant)
            Text(
                "Мое местоположение",
                Modifier
                    .padding(top = 7.dp)
                    .toggleable(value = checked.value, onValueChange = { checked.value = it }),
                fontSize = 15.sp,
                color =
                if (checked.value) {
                    MaterialTheme.colors.primary
                }
                else {
                    MaterialTheme.colors.primaryVariant
                },
                style = MaterialTheme.typography.body1
            )
        }
    }
}





@Composable
fun text(selectedCity: MutableState<String>) {
    Text(selectedCity.value, color = Color.White,
        style = MaterialTheme.typography.body1, fontSize = 30.sp)
}



@Composable
fun CustomToggleGroup() {
    val options = listOf(
        "C",
        "F",
    )

    var selectedOption by remember {
        mutableStateOf("C")
    }

    val onSelectionChange = { text: String ->
        selectedOption = text
    }

    Row (modifier = Modifier
        .clip(shape = RoundedCornerShape(size = 8.dp))
        .border(
            width = 1.dp,
            color = MaterialTheme.colors.primaryVariant,
            shape = RoundedCornerShape(size = 8.dp)
        )
    )
    {
        options.forEach { text ->
            Column {
                Text(
                    text = text,
                    style =
                    if(text == selectedOption) {
                        MaterialTheme.typography.body2
                    }
                    else {
                        MaterialTheme.typography.body2
                        },
                    color =
                    if(text == selectedOption){
                        MaterialTheme.colors.primary
                    }
                    else{
                        MaterialTheme.colors.primaryVariant
                        },
                    fontSize = 18.sp,
                    modifier = Modifier
                        .clickable {
                            onSelectionChange(text)
                        }
                        .background(
                            if (text == selectedOption) {
                                MaterialTheme.colors.secondary
                            } else {
                                Color.Transparent
                            }
                        )
                        .padding(top = 4.dp, bottom = 3.dp, start = 14.dp, end = 12.dp)

                )
            }
        }
    }
}

@Composable
fun ChangeCity(isChangeCityClicked: MutableState<Boolean>) {
    Text(
        "Сменить город",
        Modifier
            .clickable { isChangeCityClicked.value = !isChangeCityClicked.value },
        color = WhiteTransparentText,
        style = MaterialTheme.typography.body1
    )
}

@Composable
fun ChangeCityClick() {

}