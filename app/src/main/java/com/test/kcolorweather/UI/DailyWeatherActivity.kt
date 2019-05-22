package com.test.kcolorweather.UI

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.test.kcolorweather.Adapters.DayAdapter
import com.test.kcolorweather.Models.Day
import com.test.kcolorweather.R
import kotlinx.android.synthetic.main.activity_daily_weather.*

class DailyWeatherActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_daily_weather)

        intent.let {
            val  days:ArrayList<Day> = it.getParcelableArrayListExtra(MainActivity.DAYLY_WEATHER)

            val baseAdapter = DayAdapter(this, days)
            lvDaily.adapter = baseAdapter
        }

        lvDaily.emptyView = txtEmpty

    }
}
