package com.kotlin.mvvm.adapter


import androidx.databinding.ViewDataBinding
import com.kotlin.mvvm.R
import com.kotlin.mvvm.model.WeatherInfo

class WeatherAdapter<T : WeatherInfo> : BindAdapter<T, ViewDataBinding>() {


    override fun getLayout(): Int {
        return R.layout.layout_item
    }
}