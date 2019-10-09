package com.kotlin.mvvm.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.kotlin.mvvm.R
import com.kotlin.mvvm.model.WeatherInfo

class WeatherAdapter<T : WeatherInfo> : BindAdapter<T, ViewDataBinding>() {


    override fun getLayout(): Int {
        return R.layout.layout_item
    }


    override fun onDirectBindViewHolder(bean: T, holder: BindViewHolder<ViewDataBinding>, position: Int) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BindViewHolder<ViewDataBinding> {
        val binding = DataBindingUtil.inflate<ViewDataBinding>(LayoutInflater.from(parent.context), getLayout(), parent, false)
        return BindViewHolder(binding.root, binding)
    }


}