package com.test.kcolorweather.UI

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.content.res.ResourcesCompat
import android.view.View
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.test.kcolorweather.API.*
import com.test.kcolorweather.Extensions.action
import com.test.kcolorweather.Extensions.displaySnack
import com.test.kcolorweather.Models.CurrentWeather
import com.test.kcolorweather.Models.Day
import com.test.kcolorweather.Models.Hour
import com.test.kcolorweather.R
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONObject


class MainActivity : AppCompatActivity() {

    val TAG = MainActivity::class.java.simpleName

    var days:ArrayList<Day> = ArrayList()

    var hours:ArrayList<Hour> = ArrayList()

    companion object {
        val DAYLY_WEATHER = "DAILY_WEATHER"
        val HOURLY_WEATHER = "HOURLY_WEATHER"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        txtTemp.text = getString(R.string.temp_placeholder,0)
        txtPrecip.text = getString(R.string.precip_placeholder,0)

        getWeather()

    }

    private fun getWeather() {

        val latitud = 37.8267
        val longitud = -122.4233
        val language = getString(R.string.language)
        val units = getString(R.string.units)

        val url = "$DARK_SKY_URL/$API_KEY/$latitud,$longitud?lang=$language&units=$units"


        // Instantiate the RequestQueue.
        val queue = Volley.newRequestQueue(this)


        // Request a string response from the provided URL.
        val stringRequest = StringRequest(Request.Method.GET, url,
                Response.Listener { response ->

                    val responseJSON = JSONObject(response)

                    val currentWeather = getCurrentWeatherFromJson(responseJSON)

                    days = getDailyWeatherFromJson(responseJSON)

                    hours = getHourlyWeatherFromJson(responseJSON)

                    buildCurrrentWeatherUI(currentWeather)


                }, Response.ErrorListener {

                    //displayErrorMessage()
                    displayErrorMessgeFunExt()

        })

        // Add the request to the RequestQueue.
        queue.add(stringRequest)
    }

    private fun displayErrorMessgeFunExt(){

        main.displaySnack(getString(R.string.network_error), Snackbar.LENGTH_INDEFINITE){
            action(getString(R.string.retry)){
                getWeather()
            }
        }

    }

    private fun displayErrorMessage() {
        val snackbar = Snackbar.make(main,getString(R.string.network_error), Snackbar.LENGTH_INDEFINITE)
                .setAction(getString(R.string.retry), {
                    getWeather()
                })

        snackbar.show()
    }

    private fun buildCurrrentWeatherUI(currentWeather: CurrentWeather) {

        val precipProbability = (currentWeather.precip * 100).toInt()

        txtTemp.text = getString(R.string.temp_placeholder,currentWeather.temp.toInt())
        txtPrecip.text = getString(R.string.precip_placeholder,precipProbability)
        txtDescription.text = currentWeather.summary

        imvIcon.setImageDrawable(ResourcesCompat.getDrawable(resources, currentWeather.getIconResource(), null))

    }

    fun startHourlyActivity(view: View){

        val intent = Intent(this, HourlyWeatherActivity::class.java).apply {
            putParcelableArrayListExtra(HOURLY_WEATHER, hours)
        }
        startActivity(intent)

    }

    fun startDailyActivity(view: View){

        val intent = Intent(this, DailyWeatherActivity::class.java).apply {
            putParcelableArrayListExtra(DAYLY_WEATHER, days)
        }
        startActivity(intent)
    }
}
