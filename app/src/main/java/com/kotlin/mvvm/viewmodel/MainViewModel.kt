package com.kotlin.mvvm.viewmodel

import android.app.Activity
import android.content.Intent
import android.util.Log
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.databinding.BaseObservable
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kotlin.mvvm.KtApplication
import com.kotlin.mvvm.R
import com.kotlin.mvvm.api.NetworkHandler
import com.kotlin.mvvm.model.WeatherInfo
import com.kotlin.mvvm.view.MainActivity
import com.kotlin.mvvm.view.SecondActivity
import kotlinx.coroutines.*

class MainViewModel() : ViewModel() {

    companion object {
        val TAG = MainViewModel::class.java.simpleName
    }

    var start = MutableLiveData<Void>()
    private val viewModelJob = SupervisorJob()

    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    val loading = ObservableBoolean(false)

    val cityName = ObservableField<String>()

    val temperature = ObservableField<String>()


    fun queryWeather() {

        loading.set(true)
        uiScope.launch {

            val deferred = async {
                delay(1000 * 3)
                loading.set(false)
                return@async WeatherInfo("NANJING", "32")
            }

            cityName.set(
                String.format(
                    KtApplication.getContext().getString(R.string.city_name),
                    deferred.await().cityName
                )
            )

            temperature.set(
                String.format(
                    KtApplication.getContext().getString(R.string.temperature),
                    deferred.await().temperature
                )
            )
        }
    }

    fun queryWeather1() {
        loading.set(true)
        uiScope.launch(Dispatchers.Main) {
            var weatherInfo: WeatherInfo
            weatherInfo = withContext(Dispatchers.Default) {
                delay(2000)
                return@withContext WeatherInfo("NANJING", "32")
            }


            weatherInfo = withContext(Dispatchers.Default) {
                delay(2000)
                loading.set(false)
                return@withContext WeatherInfo("SHANGHAI", "36")
            }


            cityName.set(
                String.format(
                    KtApplication.getContext().getString(R.string.city_name),
                    weatherInfo.cityName
                )
            )

            temperature.set(
                String.format(
                    KtApplication.getContext().getString(R.string.temperature),
                    weatherInfo.temperature
                )
            )
        }
    }


    fun queryWeather2() {
        loading.set(true)
        uiScope.launch(Dispatchers.Main) {
            var deferred1 = async(Dispatchers.Default) {
                delay(2000)
                Log.e("wjq", "deferred1 end")
                return@async WeatherInfo("NANJING", "32")
            }


            var deferred2 = async(Dispatchers.Default) {
                delay(2000)
                loading.set(false)
                Log.e("wjq", "deferred2 end")
                return@async WeatherInfo("SHANGHAI", "36")
            }


            cityName.set(
                String.format(
                    KtApplication.getContext().getString(R.string.city_name),
                    deferred1.await().cityName
                )
            )

            temperature.set(
                String.format(
                    KtApplication.getContext().getString(R.string.temperature),
                    deferred2.await().temperature
                )
            )

        }
    }


    fun onCityNameClick(view: View) {
        start.value = null
//        if (view is TextView) {
//            Toast.makeText(KtApplication.getContext(), ""+ NetworkHandler.isConnected(), Toast.LENGTH_SHORT).show()
//
//        }


    }


    fun test(): Int {

        return if (1 > 2) {
            0
        } else {
            1
        }


    }


}