package com.test.kcolorweather.Adapters

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.test.kcolorweather.Extensions.inflate
import com.test.kcolorweather.Models.Hour
import com.test.kcolorweather.R

import kotlinx.android.synthetic.main.hourly_item.view.*

class HourAdapter(val hours: ArrayList<Hour>): RecyclerView.Adapter<HourAdapter.HourlyViewHolder>() {


    override fun onBindViewHolder(holder: HourlyViewHolder, position: Int)
       = holder.bind(hours[position])


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HourlyViewHolder
        = HourlyViewHolder(parent.inflate(R.layout.hourly_item))


    override fun getItemCount(): Int = hours.size

    class HourlyViewHolder(hourlyItemView: View): RecyclerView.ViewHolder(hourlyItemView){

        fun bind(hour: Hour) = with(itemView){

            txtHour.text = hour.getFormattedTime()

            txtHourPro.text ="${hour.precip.toInt().toString()} %"

            txtHourTemp.text = "${hour.temp.toInt().toString()} C"



        }

    }
}