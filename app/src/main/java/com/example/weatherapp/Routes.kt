package com.example.weatherapp

sealed class Routes(val route: String) {
    object Home : Routes("home")
}
