/*
Copyright (c) 2021 Kotlin Data Classes Generated from JSON powered by http://www.json2kotlin.com

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.

For support, please feel free to contact me at https://www.linkedin.com/in/syedabsar */

package com.broto.ramshyamjodumodu.weatherModels

import com.google.gson.annotations.SerializedName

data class DailyForecasts (

	@SerializedName("Date") val date : String,
	@SerializedName("EpochDate") val epochDate : Int,
	@SerializedName("Sun") val sun : Sun,
	@SerializedName("Moon") val moon : Moon,
	@SerializedName("Temperature") val temperature : Temperature,
	@SerializedName("RealFeelTemperature") val realFeelTemperature : RealFeelTemperature,
	@SerializedName("RealFeelTemperatureShade") val realFeelTemperatureShade : RealFeelTemperatureShade,
	@SerializedName("HoursOfSun") val hoursOfSun : Double,
	@SerializedName("DegreeDaySummary") val degreeDaySummary : DegreeDaySummary,
	@SerializedName("AirAndPollen") val airAndPollen : List<AirAndPollen>,
	@SerializedName("Day") val day : Day,
	@SerializedName("Night") val night : Night,
	@SerializedName("Sources") val sources : List<String>,
	@SerializedName("MobileLink") val mobileLink : String,
	@SerializedName("Link") val link : String
)