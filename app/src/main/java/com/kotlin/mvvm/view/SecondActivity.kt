package com.kotlin.mvvm.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.kotlin.mvvm.R
import com.kotlin.mvvm.databinding.SecondViewDataBinding
import com.kotlin.mvvm.viewmodel.SecondViewModel
import okhttp3.OkHttpClient

class SecondActivity : AppCompatActivity() {

    private lateinit var secondViewDataBinding: SecondViewDataBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        secondViewDataBinding = DataBindingUtil.setContentView<SecondViewDataBinding>(this, R.layout.activity_second)

        secondViewDataBinding?.secondViewModel = SecondViewModel()

        secondViewDataBinding?.secondViewModel?.loadingData()

    }


}