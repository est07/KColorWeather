package com.test.kcolorweather.UI

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast
import com.test.kcolorweather.Adapters.HourAdapter
import com.test.kcolorweather.Models.Hour
import com.test.kcolorweather.R
import kotlinx.android.synthetic.main.activity_hourly_weather.*

class HourlyWeatherActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hourly_weather)

        rvHourly.layoutManager = LinearLayoutManager(this)

        intent.let {
            val hours:ArrayList<Hour> = it.getParcelableArrayListExtra(MainActivity.HOURLY_WEATHER)

            Toast.makeText(this, hours[0].toString(), Toast.LENGTH_LONG).show()

            rvHourly.adapter = HourAdapter(hours)
        }
    }
}
