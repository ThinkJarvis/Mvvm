package com.kotlin.mvvm.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.kotlin.mvvm.KtApplication
import com.kotlin.mvvm.R
import com.kotlin.mvvm.adapter.BindAdapter
import com.kotlin.mvvm.adapter.WeatherAdapter
import com.kotlin.mvvm.databinding.MainViewDataBinding
import com.kotlin.mvvm.model.WeatherInfo
import com.kotlin.mvvm.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.layout_item.*
import kotlinx.coroutines.*


class MainActivity : AppCompatActivity() {

    private lateinit var mainViewDataBinding: MainViewDataBinding
    lateinit var mainVM : MainViewModel
    private val linearLayoutManager: LinearLayoutManager by lazy {
        LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mainViewDataBinding =
            DataBindingUtil.setContentView<MainViewDataBinding>(this, R.layout.activity_main)
        mainVM = ViewModelProviders.of(this).get(MainViewModel::class.java)
        mainViewDataBinding.mainViewModel =mainVM

        mainVM.queryWeather2()

//        buildRecycler()
        mainVM.start.observe(this, Observer {
            if (true)
                startActivity(Intent(this@MainActivity,SecondActivity::class.java))
        })


    }

    fun buildRecycler() {
        var weatherAdapter = WeatherAdapter<WeatherInfo>()
        recycler_view.layoutManager = linearLayoutManager
        recycler_view.adapter = weatherAdapter


        var dataList = mutableListOf<WeatherInfo>()
        for (i in 0..10) {
            var weatherInfo: WeatherInfo = WeatherInfo("南京", "0")
            weatherInfo.cityName = "南京" + i
            weatherInfo.temperature = "" + i
            dataList.add(weatherInfo)
        }

        weatherAdapter.setDatas(dataList)

        weatherAdapter.addOnItemClickListener(object : BindAdapter.OnItemClickListener<WeatherInfo> {
            override fun itemClick(bean: WeatherInfo, view: View, position: Int) {

            }

        })
    }



    fun handleClick() {

    }





}


