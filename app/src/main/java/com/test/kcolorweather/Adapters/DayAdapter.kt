package com.test.kcolorweather.Adapters

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.test.kcolorweather.Extensions.inflate
import com.test.kcolorweather.Models.Day
import com.test.kcolorweather.R

class DayAdapter(val context:Context, val dataSource:ArrayList<Day>):BaseAdapter() {

    override fun getView(position: Int,currentView: View?, parentView: ViewGroup): View {

        val viewHolder:ViewHolder
        val view:View

        if (currentView == null){
            view = parentView.inflate(R.layout.daily_item)
            viewHolder = ViewHolder(view)
            view.tag = viewHolder
        } else{

            viewHolder = currentView.tag as ViewHolder
            view = currentView

        }

        val currentDay = dataSource[position]

        viewHolder.apply {

            txtDay.text = currentDay.getFormattedTime()
            txtMin.text = "Min ${currentDay.minTemp.toInt()} C"
            txtMax.text = "Max ${currentDay.maxTemp.toInt()} C"

        }

        return view

    }

    override fun getItem(position: Int): Any {
        return dataSource[position]
    }

    override fun getItemId(p0: Int): Long {
        return 0
    }

    override fun getCount(): Int {
        return dataSource.size
    }

    private class ViewHolder(view: View){

        val txtDay:TextView = view.findViewById(R.id.txtDay)
        val txtMin:TextView = view.findViewById(R.id.txtMin)
        val txtMax:TextView = view.findViewById(R.id.txtMax)
    }
}