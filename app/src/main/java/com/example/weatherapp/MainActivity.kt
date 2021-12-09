package com.example.weatherapp

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import retrofit2.Response



class MainActivity : ComponentActivity() {
    lateinit var activityViewModel: ActivityViewModel
    lateinit var reqResults : WeatherResponse

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        activityViewModel = ViewModelProvider(this).get(ActivityViewModel::class.java)
        observeGetWeather()

        setContent{
            if(::reqResults.isInitialized)
            ScreenMain(reqResults)
        }

        //context = this

    }

    private fun observeGetWeather() {
        activityViewModel.simpleLiveData.observe(this, {
            activityViewModel.getWeatherOfCity("Омск")
            it.let{
                reqResults = it.data!!.body()!!
            }
        })

    }

}



@Composable
fun ScreenMain(result: WeatherResponse) {

    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Routes.Home.route) {

        // First route : Home
        composable(Routes.Home.route) {

            // Lay down the Home Composable
            // and pass the navController
            Home(result)
        }
    }
}



