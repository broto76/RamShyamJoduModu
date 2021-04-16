/* 
Copyright (c) 2021 Kotlin Data Classes Generated from JSON powered by http://www.json2kotlin.com

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.

For support, please feel free to contact me at https://www.linkedin.com/in/syedabsar */

package com.broto.ramshyamjodumodu.weatherModels

import com.google.gson.annotations.SerializedName

data class Day (

	@SerializedName("Icon") val icon : Int,
	@SerializedName("IconPhrase") val iconPhrase : String,
	@SerializedName("HasPrecipitation") val hasPrecipitation : Boolean,
	@SerializedName("ShortPhrase") val shortPhrase : String,
	@SerializedName("LongPhrase") val longPhrase : String,
	@SerializedName("PrecipitationProbability") val precipitationProbability : Int,
	@SerializedName("ThunderstormProbability") val thunderstormProbability : Int,
	@SerializedName("RainProbability") val rainProbability : Int,
	@SerializedName("SnowProbability") val snowProbability : Int,
	@SerializedName("IceProbability") val iceProbability : Int,
	@SerializedName("Wind") val wind : Wind,
	@SerializedName("WindGust") val windGust : WindGust,
	@SerializedName("TotalLiquid") val totalLiquid : TotalLiquid,
	@SerializedName("Rain") val rain : Rain,
	@SerializedName("Snow") val snow : Snow,
	@SerializedName("Ice") val ice : Ice,
	@SerializedName("HoursOfPrecipitation") val hoursOfPrecipitation : Float,
	@SerializedName("HoursOfRain") val hoursOfRain : Float,
	@SerializedName("HoursOfSnow") val hoursOfSnow : Float,
	@SerializedName("HoursOfIce") val hoursOfIce : Float,
	@SerializedName("CloudCover") val cloudCover : Int
)