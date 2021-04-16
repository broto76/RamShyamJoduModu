package com.broto.ramshyamjodumodu

data class WeatherDailyItem(
    var date: String,
    var daySummary: String,
    var maxTemp: Float,
    var minTemp: Float
)