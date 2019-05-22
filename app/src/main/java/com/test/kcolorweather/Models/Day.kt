package com.test.kcolorweather.Models

import android.os.Parcel
import android.os.Parcelable
import java.text.SimpleDateFormat
import java.util.*

data class Day(val time:Long, val minTemp:Double, val maxTemp:Double, val timezone:String): Parcelable {

    constructor(parcel: Parcel) : this(
            parcel.readLong(),
            parcel.readDouble(),
            parcel.readDouble(),
            parcel.readString())


    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeLong(time)
        parcel.writeDouble(minTemp)
        parcel.writeDouble(maxTemp)
        parcel.writeString(timezone)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Day> {
        override fun createFromParcel(parcel: Parcel): Day {
            return Day(parcel)
        }

        override fun newArray(size: Int): Array<Day?> {
            return arrayOfNulls(size)
        }
    }

    fun getFormattedTime():String{

        val formatter = SimpleDateFormat("EEEE", Locale.US)

        formatter.timeZone = TimeZone.getTimeZone(timezone)

        val date = Date(time*1000)

        val dayOfTheWeek = formatter.format(date)

        return dayOfTheWeek

    }


}