package com.broto.ramshyamjodumodu

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class WeatherDailyListAdapter(private var weatherList: List<WeatherDailyItem>):
    RecyclerView.Adapter<WeatherDailyListAdapter.WeatherListItemHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherListItemHolder {
        return WeatherListItemHolder(LayoutInflater.from(parent.context)
            .inflate(R.layout.weather_daily_item, parent, false))
    }

    override fun getItemCount(): Int {
        return weatherList.size
    }

    override fun onBindViewHolder(holder: WeatherListItemHolder, position: Int) {
        val data = weatherList[position]

        holder.date.text = data.date
        holder.summary.text = data.daySummary
        holder.maxTemp.text = data.maxTemp.toString()
        holder.minTemp.text = data.minTemp.toString()
    }

    inner class WeatherListItemHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        var date = itemView.findViewById<TextView>(R.id.tv_item_weather_date)
        var summary = itemView.findViewById<TextView>(R.id.tv_item_weather_summary)
        var maxTemp = itemView.findViewById<TextView>(R.id.tv_item_weather_maxTemp)
        var minTemp = itemView.findViewById<TextView>(R.id.tv_item_weather_minTemp)
    }
}