package com.kotlin.mvvm.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.kotlin.mvvm.R
import com.kotlin.mvvm.databinding.SecondViewDataBinding
import com.kotlin.mvvm.viewmodel.SecondViewModel
import okhttp3.OkHttpClient

class SecondActivity : AppCompatActivity() {

    private lateinit var secondViewDataBinding: SecondViewDataBinding
    private lateinit var secondVM: SecondViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        secondViewDataBinding = DataBindingUtil.setContentView<SecondViewDataBinding>(this, R.layout.activity_second)
        secondVM = ViewModelProviders.of(this).get(SecondViewModel::class.java)
        secondViewDataBinding.secondViewModel =  secondVM

        secondVM.loadingData()

    }


}