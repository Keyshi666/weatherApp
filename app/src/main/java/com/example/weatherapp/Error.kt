package com.example.weatherapp

import com.google.gson.annotations.SerializedName

data class Error(
        @SerializedName("cod")
        val errorCode: Int?,
        @SerializedName("message")
        val errorMsg: String?,
    )
