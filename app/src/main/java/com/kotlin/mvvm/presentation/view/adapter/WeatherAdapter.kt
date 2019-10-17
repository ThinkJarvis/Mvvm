package com.kotlin.mvvm.presentation.view.adapter


import com.kotlin.mvvm.R
import com.kotlin.mvvm.data.model.WeatherInfo
import com.kotlin.mvvm.presentation.platform.BindAdapter

class WeatherAdapter<T : WeatherInfo> : BindAdapter<T>() {


    override fun getLayout() = R.layout.layout_item
}