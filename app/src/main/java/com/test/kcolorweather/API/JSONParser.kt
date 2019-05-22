package com.test.kcolorweather.API

import com.test.kcolorweather.Models.CurrentWeather
import com.test.kcolorweather.Models.Day
import org.json.JSONObject
import com.test.kcolorweather.Extensions.iterator
import com.test.kcolorweather.Models.Hour


fun getCurrentWeatherFromJson(response: JSONObject):CurrentWeather{

    val currentJson = response.getJSONObject(currently)

    with(currentJson){


        val currentWeather = CurrentWeather(
                getString(icon),
                getString(summary),
                getDouble(temperature),
                getDouble(precipProbability))

        return currentWeather
    }
}

fun getDailyWeatherFromJson(response: JSONObject):ArrayList<Day>{

    val dailyJSON = response.getJSONObject(daily)

    val timeZone =response.getString(timezone)

    val dayJSONArray = dailyJSON.getJSONArray(data)

    val days = ArrayList<Day>()

    for (jsonDay in dayJSONArray){


        val minTemp = jsonDay.getDouble(temperatureMin)
        val maxTemp = jsonDay.getDouble(temperatureMax)
        val time = jsonDay.getLong(time)


        days.add(Day(time, minTemp, maxTemp, timeZone))

    }
    return days
}


fun getHourlyWeatherFromJson(response: JSONObject):ArrayList<Hour>{

    val hourlyJSON = response.getJSONObject(hourly)

    val timeZone =response.getString(timezone)

    val hourJSONArray = hourlyJSON.getJSONArray(data)

    val hours = ArrayList<Hour>()

    for (jsonHour in hourJSONArray){


        val time = jsonHour.getLong(time)
        val temperature = jsonHour.getDouble(temperature)
        val precipProb = jsonHour.getDouble(precipProbability)

        hours.add(Hour(time, temperature, precipProb, timeZone))

    }
    return hours
}
