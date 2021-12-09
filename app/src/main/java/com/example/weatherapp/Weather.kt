package com.example.weatherapp
import com.google.gson.annotations.SerializedName

data class  Weather(
    @field:SerializedName("name")
    val city: String? = null,

    @field:SerializedName("description")
    val description: String? = null,

    @field:SerializedName("temp")
    val temp_F: Double? = null,

    @field:SerializedName("speed")
    val wind_speed: Int? = null,

    @field:SerializedName("deg")
    val wind_deg: Double? = null,

    @field:SerializedName("pressure")
    val pressure: Int? = null,

    val temp_c : Double? =
        if(temp_F != null) (temp_F-32)/1.8
        else null,

    @field:SerializedName("humidity")
    val humidity: Int? = null,
)

