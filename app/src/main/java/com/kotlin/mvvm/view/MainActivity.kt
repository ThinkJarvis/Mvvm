package com.kotlin.mvvm.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.kotlin.mvvm.R
import com.kotlin.mvvm.adapter.WeatherAdapter
import com.kotlin.mvvm.databinding.MainViewDataBinding
import com.kotlin.mvvm.extension.setContentView
import com.kotlin.mvvm.extension.toast
import com.kotlin.mvvm.model.WeatherInfo
import com.kotlin.mvvm.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private lateinit var mainViewDataBinding: MainViewDataBinding
    private lateinit var mainVM: MainViewModel
    private val linearLayoutManager: LinearLayoutManager by lazy {
        LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mainViewDataBinding = setContentView { R.layout.activity_main }

        mainVM = ViewModelProviders.of(this)[MainViewModel::class.java]

        mainViewDataBinding.mainViewModel = mainVM

        mainVM.queryWeather2()

        buildRecycler()

        mainVM.start.observe(this, Observer {
            startActivity(Intent(this@MainActivity, SecondActivity::class.java))
        })

    }

    fun buildRecycler() {
        var weatherAdapter = WeatherAdapter<WeatherInfo>()

        recycler_view.run {
            layoutManager = linearLayoutManager
            adapter = weatherAdapter
        }

        var dataList = mutableListOf<WeatherInfo>()
        for (i in 0..10) {
            dataList.add(WeatherInfo("南京", "0").apply {
                cityName = "南京" + i
                temperature = "" + i
            })
        }

        weatherAdapter.run {
            collection = dataList
            onItemClickListener = handleClick()
        }

    }

    private fun <T> handleClick() = { bean: T, view: View, position: Int ->
        toast("$position")
    }


}


