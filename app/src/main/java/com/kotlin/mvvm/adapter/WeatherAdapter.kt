package com.kotlin.mvvm.adapter


import com.kotlin.mvvm.R
import com.kotlin.mvvm.model.WeatherInfo

class WeatherAdapter<T : WeatherInfo> : BindAdapter<T>() {


    override fun getLayout(): Int {
        return R.layout.layout_item
    }
}