package com.broto.ramshyamjodumodu

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.broto.ramshyamjodumodu.weatherModels.WeatherResponse
import kotlinx.android.synthetic.main.activity_weather.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class WeatherActivity : AppCompatActivity() {

    private val TAG = "WeatherActivity"

    private var username: String? = null
    private var mWeatherList: ArrayList<WeatherDailyItem> = ArrayList<WeatherDailyItem>()
    private var adapter: WeatherDailyListAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weather)

        rl_weather_root.visibility = View.INVISIBLE

        rv_weather_data.layoutManager = LinearLayoutManager(applicationContext)

        username = intent.getStringExtra(Constants.KEY_USERNAME)
        tv_weather_username.text = "Yoo\n $username"
    }

    override fun onResume() {
        getweather()
        super.onResume()
    }

    fun getweather() {
        val retrofit = Retrofit.Builder()
            .baseUrl("http://dataservice.accuweather.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(WeatherService::class.java)
        //Log.d(TAG,"URL: " + service.getWeatherDataRAW(Constants.LOCATION_KEY, Constants.ACCU_API_KEY, true, true).request().url().toString())
        val call = service.getWeatherDataRAW(Constants.LOCATION_KEY, Constants.ACCU_API_KEY,
            details = true,
            metric = true
        )

        call.enqueue(object : Callback<WeatherResponse> {

            override fun onFailure(call: Call<WeatherResponse>?, t: Throwable?) {
                Log.d(TAG, "Failed REST API: ${t?.message}")
            }

            override fun onResponse(call: Call<WeatherResponse>?, response: Response<WeatherResponse>?) {
                val body = response?.body()

                Log.d(TAG, "Response: ${body.toString()}")
                Log.d(TAG, "Response: ${response?.code()}")
                //Toast.makeText(this@WeatherActivity, body?.headline?.text, Toast.LENGTH_SHORT).show()
                runOnUiThread {
                    tv_weather_summary.text = body?.headline?.text

                    var weatherList = body?.dailyForecasts
                    if (weatherList == null) {
                        Log.d(TAG,"Weather details not found")
                        finish()
                        return@runOnUiThread
                    }

                    for (item in weatherList) {
                        mWeatherList.add(WeatherDailyItem(
                            item.date,
                            item.day.longPhrase,
                            item.temperature.maximum.value.toFloat(),
                            item.temperature.minimum.value.toFloat()))
                        Log.d(TAG, "Date: ${item.date}")
                        Log.d(TAG, "Summary: ${item.day.longPhrase}")
                        Log.d(TAG, "MaxTemp: ${item.temperature.maximum.value}")
                        Log.d(TAG, "MinTemp: ${item.temperature.minimum.value}")
                    }

                    adapter = WeatherDailyListAdapter(mWeatherList)
                    rv_weather_data.adapter = adapter

                    rl_weather_root.visibility = View.VISIBLE
                }
            }

        })
    }
}
